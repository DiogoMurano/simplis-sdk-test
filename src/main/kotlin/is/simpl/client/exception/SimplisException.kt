package `is`.simpl.client.exception

class SimplisException(
    message: String,
    rootCause: Exception? = null
) : RuntimeException(message, rootCause)
