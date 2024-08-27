package com.iamnaran.firefly.ui.main.interest.components.account_sheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.iamnaran.firefly.data.dto.AccountModel

@Composable
fun AccountList(accounts: List<AccountModel>, onAccountClick: (AccountModel) -> Unit) {
    LazyColumn {
        items(accounts.size) { index ->
            AccountItemHeader(accounts[index], onAccountClick)
        }
        item {
            AccountItemFooter() {

            }
        }
    }
}


