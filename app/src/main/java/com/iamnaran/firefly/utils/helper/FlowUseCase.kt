package com.iamnaran.firefly.utils.helper

import com.iamnaran.firefly.di.IoCoroutineScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out T>(private val coroutineScope: CoroutineScope) {
    protected abstract fun execute(params: Params? = null): Flow<T>

    operator fun invoke(params: Params? = null): Flow<T> =
        execute(params).flowOn(coroutineScope.coroutineContext)
}