package com.iamnaran.firefly.ui.main.profile

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.domain.usecase.auth.GetUserByIdUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.utils.helper.AppLocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val gson: Gson,
    private val appLocaleManager: AppLocaleManager
) : BaseViewModel() {

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState


    private val _selectedLanguage = MutableStateFlow(appLocaleManager.getLanguageCode())
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    private var loggedInUserId: String = ""

    init {
        loggedInUserId = preferenceHelper.getLoggedInUserId()!!
        getLoggedInUser(loggedInUserId)
    }

    fun changeLanguage(languageCode: String) {
        appLocaleManager.changeLanguage(languageCode)
        _selectedLanguage.value = languageCode
    }

    private fun getLoggedInUser(userId: String) {
        viewModelScope.launch {
            getUserByIdUseCase(userId).onEach { userDetails ->
                _profileState.value = ProfileState(userDetails)

            }.launchIn(this)
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            preferenceHelper.clearPreference()
        }
    }


}