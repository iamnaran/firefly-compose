package com.iamnaran.firefly.utils.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine


fun <T> Throwable.asFlow(): Flow<T> = flow {
    emit(suspendCancellableCoroutine { cancellableContinuation ->
        cancellableContinuation.cancel(this@asFlow)
    })
}

fun <T1, T2> CoroutineScope.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    collectBlock: (suspend (T1, T2) -> Unit)
) {
    launch {
        flow1.combine(flow2) { v1, v2 ->
            collectBlock.invoke(v1, v2)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3> CoroutineScope.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    collectBlock: (suspend (T1, T2, T3) -> Unit)
) {
    launch {
        combine(flow1, flow2, flow3) { v1, v2, v3 ->
            collectBlock.invoke(v1, v2, v3)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T1, T2, T3, T4> CoroutineScope.combineFlows(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    collectBlock: (suspend (T1, T2, T3, T4) -> Unit)
) {
    launch {
        combine(flow1, flow2, flow3, flow4) { v1, v2, v3, v4 ->
            collectBlock.invoke(v1, v2, v3, v4)
        }.collect {
            // Empty collect block to trigger ^
        }
    }
}

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var lastEmissionTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        val mayEmit = currentTime - lastEmissionTime > windowDuration
        if (mayEmit) {
            lastEmissionTime = currentTime
            emit(upstream)
        }
    }
}