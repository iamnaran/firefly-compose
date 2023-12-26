package com.iamnaran.firefly.utils

import com.iamnaran.firefly.data.api.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable.message.toString(), it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}


inline fun <ResultType> networkBoundResourceOnly(
    crossinline fetch: suspend () -> ResultType
) = flow {
    emit(Resource.Loading(null))
    try {
        val result = fetch()
        emit(Resource.Success(result))
    } catch (throwable: Throwable) {
        emit(Resource.Error(throwable.message.toString(), null))
    }
}