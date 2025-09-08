package com.iamnaran.firefly.ui.appcomponent.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheetDialog(
    onBackPressed: () -> Unit,
    bottomSheetContent: @Composable () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    BackPressHandler {
        onBackPressed()
    }

    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            decorFitsSystemWindows = true,

        ),
        onDismissRequest = {

        }
    ){
        Surface(modifier = Modifier.fillMaxSize()) {

            bottomSheetContent()

        }
    }

}