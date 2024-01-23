package com.iamnaran.firefly.ui.main

import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
    ) :
    BaseViewModel() {


    fun isUserAuthenticated(): Boolean {
        return preferenceHelper.getLoggedInStatus()
    }


}