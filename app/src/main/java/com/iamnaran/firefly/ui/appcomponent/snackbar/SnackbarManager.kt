package com.iamnaran.firefly.ui.appcomponent.snackbar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class SnackEvent(
    val message: String,
    val action: SnackAction? = null
)

data class SnackAction(
    val name: String,
    val action: suspend () -> Unit
)

object SnackbarManager {

    private val _events = Channel<SnackEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackEvent) {
        _events.send(event)
    }
}