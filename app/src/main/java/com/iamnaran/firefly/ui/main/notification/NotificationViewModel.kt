package com.iamnaran.firefly.ui.main.notification

import androidx.lifecycle.viewModelScope
import com.iamnaran.firefly.data.remote.Resource
import com.iamnaran.firefly.domain.usecase.recipe.GetRecipeUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
) : BaseViewModel() {


    private val _notificationState = MutableStateFlow(NotificationState())
    val notificationState: StateFlow<NotificationState> = _notificationState


    init {
        getProducts()
    }


    private fun getProducts() {

        viewModelScope.launch {
            getRecipeUseCase().collectLatest { recipeResource ->
                when (recipeResource) {
                    is Resource.Loading ->{
                        _notificationState.value = NotificationState(recipeResource.data!!)
                    }
                    is Resource.Success -> {
                        _notificationState.value = NotificationState(recipeResource.data!!)
                    }
                    else -> {
                    }
                }
            }
        }
    }

}