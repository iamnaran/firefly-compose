package com.iamnaran.firefly.utils.exception

data class Redirect(
    val redirect: RedirectType,
    val redirectObject: Any? = null,
)

enum class RedirectType