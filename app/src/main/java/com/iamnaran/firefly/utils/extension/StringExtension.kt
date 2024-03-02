package com.iamnaran.firefly.utils.extension

fun String.capitalizeFirstChar(): String {
    return replaceFirstChar {
        it.uppercaseChar()
    }
}

fun String.getFirstTwoWords(): String {
    val words = this.split(" ")
    return if (words.size >= 2) {
        "${words[0]} ${words[1]}"
    } else {
        this
    }
}