package com.iamnaran.firefly.data.dto


data class AccountModel(
    val id: Int,
    val name: String,
    val username: String,
    val image: String,
    var isChecked: Boolean
)