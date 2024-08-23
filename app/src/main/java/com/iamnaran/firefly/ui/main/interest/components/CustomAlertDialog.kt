package com.iamnaran.firefly.ui.main.interest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.os.trace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    dialogTitle: String,
    dialogSubTitle: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {


    BasicAlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = true,
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        ),
        onDismissRequest = {
            onDismissRequest()

        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.5f)
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(20.dp)
        ) {

            Column {

                Text(text = "Logout Confirmation")

                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = "Are you sure you want to logout form app?")

                Spacer(modifier = Modifier.padding(10.dp))

                Row {
                    TextButton(onClick = {

                    }) {
                        Text(text = "Yes")

                    }

                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "No")

                    }


                }


            }


        }

    }


}