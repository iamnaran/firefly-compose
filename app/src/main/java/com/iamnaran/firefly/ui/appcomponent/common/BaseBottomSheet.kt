package com.iamnaran.firefly.ui.appcomponent.common

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    onDismissed: () -> Unit,
    bottomSheetContent: @Composable () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)


    Scaffold { _ ->
        // Screen content

        ModalBottomSheet(
            onDismissRequest = {

            },
            sheetState = sheetState
        ) {
            bottomSheetContent()
            // Sheet content
            Button(onClick = {

            }) {
                Text("Hide bottom sheet")
            }
        }
    }

}