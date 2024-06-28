package com.assessment.insightglobal.utils

/*API response handler with Success, Failure, Loading*/
sealed class ResponseHandler<T>(
    val status: Status,
    val message: String?,
    var data: T?,
) {
    class Success<T>(data: T?) : ResponseHandler<T>(Status.SUCCESS, null, data)
    class Failure<T>(errorMessage: String?) : ResponseHandler<T>(Status.FAILURE, errorMessage, null)
    class Loading<T> : ResponseHandler<T>(Status.LOADING, null, null)
}
