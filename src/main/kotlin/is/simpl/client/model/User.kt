package `is`.simpl.client.model

import `is`.simpl.client.Model

data class User(
    val username: String,
    val country: Country,
    val email: String
): Model() {

    enum class Country {
        BRAZIL
    }

}
