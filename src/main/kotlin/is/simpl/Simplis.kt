package `is`.simpl

import io.ktor.client.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import `is`.simpl.client.exception.SimplisException

object Simplis {

    private var internalApiUrl: String? = null
    private var internalApiKey: String? = null

    val name = "Simplis-SDK"
    val apiUrl = internalApiUrl ?: throw SimplisException("Simplis URL configuration pending.")
    val apiKey = internalApiKey ?: throw SimplisException("Simplis api key configuration pending.")

    fun configure(apiUrl: String, apiKey: String) {
        this.internalApiUrl = apiUrl
        this.internalApiKey = apiKey
    }

    internal val httpClient = HttpClient {
        install(Logging)
        install(HttpCache)
        install(ContentNegotiation) {
            jackson()
        }
    }

    val defaultHeaders = mapOf(
        HttpHeaders.Accept to "application/json",
        HttpHeaders.ContentType to "application/json",
        HttpHeaders.Authorization to apiKey,
        HttpHeaders.UserAgent to name
    )

}
