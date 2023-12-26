package com.iamnaran.firefly.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.domain.usecase.AuthUseCase
import com.iamnaran.firefly.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authUseCase: AuthUseCase) :
    BaseViewModel() {

    private var _loginStateResponse = MutableLiveData<Boolean>()
    val loginStateResponse: MutableLiveData<Boolean> get() = _loginStateResponse

    fun login() {

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