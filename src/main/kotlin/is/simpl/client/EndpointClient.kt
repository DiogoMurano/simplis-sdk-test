package `is`.simpl.client

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import `is`.simpl.Simplis
import `is`.simpl.client.exception.SimplisClientException
import `is`.simpl.client.model.UserClient
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass

abstract class Model

abstract class EndpointClient<T : Model> {

    fun <T> requestAndReturnModel(f: HttpRequestBuilder.() -> Unit): T = runBlocking {
        val response = Simplis.httpClient.request {

            url {
                host = Simplis.apiUrl
                path(UserClient.endpointPath)
            }

            headers {
                Simplis.defaultHeaders.forEach { append(it.key, it.value) }
            }

            f.invoke(this)
        }

        try {
            if (response.status.isSuccess()) {
//                Simplis.objectMapper.readValue(response.bodyAsText(), object : TypeReference<T>() {})
                response.body(
                    TypeInfo(
                        modelClass,
                        modelClass.java
                    )
                )
            } else throw SimplisClientException(
                message = response.bodyAsText(),
                httpStatus = response.status.value
            )
        } catch (exception: Exception) {
            throw SimplisClientException(
                message = "Client message: ${response.bodyAsText()}. Exception message: ${exception.message}",
                httpStatus = response.status.value,
                rootCause = exception
            )
        }

    }

    abstract val modelClass: KClass<T>
    abstract val endpointPath: String

}
