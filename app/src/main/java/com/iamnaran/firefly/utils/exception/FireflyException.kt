package com.iamnaran.firefly.utils.exception

sealed class FireflyException(
    open val code: Int,
    val type: ExceptionType,
    override val message: String?,
) : Throwable(message) {

    data class AlertException(
        override val code: Int,
        val alertDialog: AlertDialog,
    ) : FireflyException(code, ExceptionType.AlertDialog, null)

    data class InlineException(
        override val code: Int,
        val tags: List<Tag>,
    ) : FireflyException(code, ExceptionType.Inline, null)

    data class RedirectException(
        override val code: Int,
        val redirect: RedirectType,
    ) : FireflyException(code, ExceptionType.Redirect, null)

    data class SnackBarException(
        override val code: Int = -1,
        override val message: String,
    ) : FireflyException(code, ExceptionType.Snack, message)

    data class ToastException(
        override val code: Int,
        override val message: String,
    ) : FireflyException(code, ExceptionType.Toast, message)

    data class OnPageException(
        override val code: Int,
        override val message: String,
    ) : FireflyException(code, ExceptionType.OnPage, message)
}