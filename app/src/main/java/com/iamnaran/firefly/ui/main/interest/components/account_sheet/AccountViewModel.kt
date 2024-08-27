package com.iamnaran.firefly.ui.main.interest.components.account_sheet

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iamnaran.firefly.data.dto.AccountModel
import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val gson: Gson,
) : BaseViewModel() {

    private val _accountState = MutableStateFlow(AccountState())
    val accountState: StateFlow<AccountState> = _accountState

    private var loggedInUserId: String = ""

    init {
        loggedInUserId = preferenceHelper.getLoggedInUserId()!!
        getLoggedInUser(loggedInUserId)
        generateInterest()
    }

    private fun generateInterest() {
        val dataList = mutableListOf<AccountModel>()
        for (i in 1..2) {
            if (i % 2 == 0) {
                dataList.add(AccountModel(Random.nextInt(101), "The Crafter Penguins $i", "crafter_penguins","",true))
            } else {
                dataList.add(AccountModel(Random.nextInt(101), "Ruby Personal $i", "ruby_personal","",false))
            }
        }
        _accountState.value.accounts = dataList

    }

    fun updateTask(accountModel: AccountModel) {
        val account = _accountState.value.accounts.find { it.id == accountModel.id }

        // If the task is found, toggle its checked state
        if (account != null) {
            account.isChecked = !account.isChecked
        }
    }


    private fun getLoggedInUser(userId: String) {
        viewModelScope.launch {


        }
    }

}