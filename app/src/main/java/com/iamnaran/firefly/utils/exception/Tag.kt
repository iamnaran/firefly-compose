package com.iamnaran.firefly.utils.exception

data class Tag(
    val name: TagType,
    val message: String?,
)

enum class TagType