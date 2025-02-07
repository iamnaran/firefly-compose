package com.iamnaran.firefly.ui.main.settings

import android.content.Context
import com.google.gson.Gson
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.helper.AppLocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val appLocaleManager = AppLocaleManager()
    private val _settingState = MutableStateFlow(SettingState())
    val settingState: StateFlow<SettingState> = _settingState

    private var loggedInUserId: String = ""

    init {
        loggedInUserId = preferenceHelper.getLoggedInUserId()!!
        loadInitialLanguage()

    }

    private fun loadInitialLanguage() {
        val currentLanguage = appLocaleManager.getLanguageCode(context)
        _settingState.value = _settingState.value.copy(selectedLanguage = currentLanguage)
    }

    fun changeLanguage(languageCode: String) {
        appLocaleManager.changeLanguage(context, languageCode)
        _settingState.value = _settingState.value.copy(selectedLanguage = languageCode)
    }


}