package com.iamnaran.firefly.utils.extension

class StringExtension {
    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}