package com.iamnaran.firefly.ui.main.interest.components.account_sheet

import com.iamnaran.firefly.data.dto.AccountModel


data class AccountState(
    var accounts: List<AccountModel> = emptyList()
)