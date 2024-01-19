package com.iamnaran.firefly.utils.exception

data class AlertDialog(
    val title: String,
    val message: String,
    val positiveMessage: String? = null,
    val positiveAction: ActionType? = null,
    val positiveObject: Any? = null,
    val negativeMessage: String? = null,
    val negativeAction: ActionType? = null,
    val negativeObject: Any? = null
)


enum class ActionType {
    RETRY_API, OPEN_PERMISSION,
}