package `is`.simpl.client.model

import io.ktor.client.request.*
import `is`.simpl.client.EndpointClient
import kotlin.reflect.KClass

object UserClient : EndpointClient<User>() {

    fun create(user: User): User = requestAndReturnModel {

        data class UserCreateRequest(
            val name: String,
            val email: String,
            val country: String
        )

        val (username, country, email) = user

        setBody(
            body = UserCreateRequest(
                name = username,
                country = country.name,
                email = email
            )
        )
    }

    override val modelClass: KClass<User>
        get() = User::class

    override val endpointPath: String
        get() = "/api/v1/users"

}
