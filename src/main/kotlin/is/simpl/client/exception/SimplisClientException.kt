package `is`.simpl.client.exception

class SimplisClientException(
    message: String,
    httpStatus: Int,
    rootCause: Exception? = null
) : RuntimeException()
