package com.iamnaran.firefly.ui.main.interest.components.account_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFullScreenBottomSheetDialog(
    accountViewModel: AccountViewModel = hiltViewModel(),
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onActionConfirmation: () -> Unit,
) {
    val interestState = accountViewModel.accountState.collectAsStateLifecycleAware()

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.safeDrawing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // Sheet content
            Button(onClick = {
                onDismissRequest()
            }) {
                Text("Hide bottom sheet")
            }

            AccountList(interestState.value.accounts){

            }



            Spacer(modifier = Modifier.height(60.dp))
        }
    }


}