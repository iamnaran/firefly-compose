package com.iamnaran.firefly.ui.main.interest

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.data.dto.InterestModel
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class InterestViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val gson: Gson,
) : BaseViewModel() {

    private val _interestState = MutableStateFlow(InterestState())
    val interestState: StateFlow<InterestState> = _interestState

    private var loggedInUserId: String = ""

    init {
        loggedInUserId = preferenceHelper.getLoggedInUserId()!!
        getLoggedInUser(loggedInUserId)
        generateInterest()
    }

    private fun generateInterest() {
        val dataList = mutableListOf<InterestModel>()
        for (i in 1..10) {
            if (i % 2 == 0) {
                dataList.add(InterestModel(Random.nextInt(101), "Interest $i", true))
            } else {
                dataList.add(InterestModel(Random.nextInt(101), "Interest $i", false))
            }
        }
        _interestState.value.interests = dataList

    }

    fun updateTask(interestModel: InterestModel) {
        val interest = _interestState.value.interests.find { it.id == interestModel.id }

        // If the task is found, toggle its checked state
        if (interest != null) {
            interest.isChecked = !interest.isChecked
        }
    }


    private fun getLoggedInUser(userId: String) {
        viewModelScope.launch {


        }
    }

}