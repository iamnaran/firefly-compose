package com.iamnaran.firefly.ui.main.settings

import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.helper.AppLocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val appLocaleManager: AppLocaleManager,
) : BaseViewModel() {


    private val _settingState = MutableStateFlow(SettingState())
    val settingState: StateFlow<SettingState> = _settingState

    init {
        loadInitialLanguage()
    }

    private fun loadInitialLanguage() {
        val currentLanguage = appLocaleManager.getLanguageCode()
        _settingState.value = _settingState.value.copy(selectedLanguage = currentLanguage)
    }

    fun changeLanguage(languageCode: String) {
        appLocaleManager.changeLanguage(languageCode)
        _settingState.value = _settingState.value.copy(selectedLanguage = languageCode)
    }

}