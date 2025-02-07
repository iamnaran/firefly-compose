package com.iamnaran.firefly.ui.main.settings

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.domain.usecase.auth.GetUserByIdUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val gson: Gson,
) : BaseViewModel() {

    private val _profileState = MutableStateFlow(SettingState())
    val profileState: StateFlow<SettingState> = _profileState

    private var loggedInUserId: String = ""

    init {
        loggedInUserId = preferenceHelper.getLoggedInUserId()!!
        getLoggedInUser(loggedInUserId)
    }


    private fun getLoggedInUser(userId: String) {
        viewModelScope.launch {
            getUserByIdUseCase(userId).onEach { userDetails ->
                _profileState.value = SettingState(userDetails)

            }.launchIn(this)
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            preferenceHelper.clearPreference()
        }
    }


}