package com.iamnaran.firefly.utils.helper

import com.iamnaran.firefly.data.remote.Resource
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
): Flow<Resource<ResultType>> = flow {
    val data = query().first()

    val flowToEmit: Flow<Resource<ResultType>> = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            val fetched = fetch()
            saveFetchResult(fetched)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable.message ?: "Unknown Error", it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flowToEmit)
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