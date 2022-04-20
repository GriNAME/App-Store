package com.example.homestore_api.data.util

sealed class NetworkResult<T>(
    val data: T?,
    val message: String? = null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(data: T?, message: String?) : NetworkResult<T>(data, message)
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
}
