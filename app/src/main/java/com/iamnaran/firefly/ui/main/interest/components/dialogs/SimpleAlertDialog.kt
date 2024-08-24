package com.iamnaran.firefly.ui.main.interest.components.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAlertDialog(
    dialogTitle: String,
    dialogSubTitle: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.92f),
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = true,
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            ),
            shape = RoundedCornerShape(20.dp),
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Text(text = dialogTitle, fontSize = 18.sp)
            },
            text = {
                Text(text = dialogSubTitle)
            })
}