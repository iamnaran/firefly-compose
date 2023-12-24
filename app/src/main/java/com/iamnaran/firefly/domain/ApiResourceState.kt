package com.iamnaran.firefly.domain

sealed class ApiResourceState<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : ApiResourceState<T>(data = data)

    class Error<T>(message: String, data: T? = null) : ApiResourceState<T>(data = data, message = message)

    class Loading<T>(data: T? = null) : ApiResourceState<T>(data = data)

    class Internet<T>(message: String, data: T? = null) :
        ApiResourceState<T>(data = data, message = message)
}

