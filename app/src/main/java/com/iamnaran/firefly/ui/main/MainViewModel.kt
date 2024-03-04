package com.iamnaran.firefly.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.ui.navigation.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
    ) :
    BaseViewModel() {

    private val _currentRoute = mutableStateOf<String?>(AppScreen.Auth.route)
    val currentRoute: State<String?> = _currentRoute

    fun setCurrentRoute(route: String?) {
        _currentRoute.value = route
    }

    fun isUserAuthenticated(): Boolean {
        return preferenceHelper.getLoggedInStatus()
    }


}