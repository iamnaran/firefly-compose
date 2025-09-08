package com.iamnaran.firefly.utils.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out T>(private val coroutineDispatcher: CoroutineDispatcher) {
    protected abstract fun execute(params: Params? = null): Flow<T>

    operator fun invoke(params: Params? = null): Flow<T> =
        execute(params).flowOn(coroutineDispatcher)
}