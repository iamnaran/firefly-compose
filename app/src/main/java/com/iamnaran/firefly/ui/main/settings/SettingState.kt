package com.iamnaran.firefly.ui.main.settings

import com.iamnaran.firefly.data.local.entities.UserEntity


data class SettingState(
    val userEntityDetails: UserEntity? = null,
    val selectedLanguage: String = ""
)

