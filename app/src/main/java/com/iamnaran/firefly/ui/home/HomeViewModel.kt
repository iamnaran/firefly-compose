package com.iamnaran.firefly.ui.login

import android.view.contentcapture.DataRemovalRequest.LocusIdRequest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.domain.login.model.LoginRequest
import com.iamnaran.firefly.domain.login.model.LoginResponse
import com.iamnaran.firefly.repository.user.UserRepository
import com.iamnaran.firefly.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private var _loginStateResponse = MutableLiveData<Boolean>()
    val loginStateResponse: MutableLiveData<Boolean> get() = _loginStateResponse

    fun login() {
        val loginRequest = LoginRequest("nikesh@treeleaf.ai", "password")

        viewModelScope.launch {

//            userRepository.login(loginRequest).collect { response ->
//                when (response) {
//                    is Result.Success -> _loginStateResponse.value = response.data
//
//                    is Result.GenericError -> { //Error
//                    }
//                    else -> { //Loading
//                    }
//                }
//            }
        }
    }

}