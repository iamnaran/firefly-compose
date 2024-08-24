package com.iamnaran.firefly.ui.main.interest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.R
import com.iamnaran.firefly.domain.dto.InterestModel
import com.iamnaran.firefly.ui.main.interest.components.dialogs.BeautifulAlertDialog
import com.iamnaran.firefly.ui.main.interest.components.dialogs.CustomAlertDialog
import com.iamnaran.firefly.ui.main.interest.components.dialogs.FullScreenDialog
import com.iamnaran.firefly.ui.main.interest.components.dialogs.MinimalAlertDialog
import com.iamnaran.firefly.ui.main.interest.components.sheet.MyBottomSheetDialog
import com.iamnaran.firefly.ui.main.interest.components.sheet.MyFullScreenBottomSheetDialog
import com.iamnaran.firefly.ui.main.interest.components.dialogs.SimpleAlertDialog
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun InterestScreen(
    interestViewModel: InterestViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val interestState = interestViewModel.interestState.collectAsStateLifecycleAware()

    InterestContent(interestState) {
        interestViewModel.updateTask(it)

    }


}

@Composable
fun InterestContent(interestState: State<InterestState>, onInterestState: (InterestModel) -> Unit) {

    Column(
        Modifier
            .padding(MaterialTheme.dimens.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DialogsContent()

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogsContent() {

    var showSimpleDialog by remember { mutableStateOf(false) }
    var minimalDialog by remember { mutableStateOf(false) }
    var showBasicDialog by remember { mutableStateOf(false) }
    var showBeautifulDialog by remember { mutableStateOf(false) }
    var showFullScreenDialog by remember { mutableStateOf(false) }

    var showBottomSheetDialog by remember { mutableStateOf(false) }
    var showBottomSheetFullScreenDialog by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    Column() {

        when {
            showSimpleDialog -> {
                SimpleAlertDialog(
                    dialogTitle = " Aaradhya Tripathee",
                    dialogSubTitle = "Are you sure you want to logout? All caches data will be cleared.",
                    onDismissRequest = {
                        showSimpleDialog = false
                    }) {
                    showSimpleDialog = false
                }
            }
        }

        when {
            minimalDialog -> {
                MinimalAlertDialog(
                    dialogTitle = " Aaradhya Tripathee",
                    dialogSubTitle = "Are you sure you want to logout? All caches data will be cleared.",
                    onDismissRequest = {
                        minimalDialog = false
                    }) {
                    minimalDialog = false
                }
            }
        }

        when {
            showBasicDialog -> {
                CustomAlertDialog(
                    dialogTitle = "Logout Confirmation",
                    dialogSubTitle = "Are you sure you want to logout? All caches data will be cleared.",
                    onDismissRequest = {
                        showBasicDialog = false
                    }) {
                    showBasicDialog = false
                }
            }
        }

        when {
            showBeautifulDialog -> {
                BeautifulAlertDialog(
                    onDismissRequest = { showBeautifulDialog = false },
                    onConfirmation = {
                        showBeautifulDialog = false
                    },
                    painter = painterResource(id = R.drawable.ai_png),
                    imageDescription = "Sample Image"
                )
            }
        }

        when {
            showFullScreenDialog -> {
                FullScreenDialog(
                    dialogTitle = "Logout Confirmation",
                    dialogSubTitle = "Are you sure you want to logout? All caches data will be cleared.",
                    onDismissRequest = {
                        showFullScreenDialog = false
                    }) {
                    showFullScreenDialog = false
                }
            }
        }

        when {
            showBottomSheetDialog -> {
                MyBottomSheetDialog(
                    sheetState = sheetState,
                    onDismissRequest = {
                        showBottomSheetDialog = false

                    }) {
                    showBottomSheetDialog = false
                }
            }
        }

        when {
            showBottomSheetFullScreenDialog -> {
                MyFullScreenBottomSheetDialog(
                    sheetState = sheetState,
                    onDismissRequest = {
                        showBottomSheetFullScreenDialog = false
                    }) {
                    showBottomSheetFullScreenDialog = false
                }
            }
        }

        TextButton(onClick = {

            showSimpleDialog = true

        }) {
            Text(text = "Simple Alert Dialog ")

        }

        TextButton(onClick = {

            minimalDialog = true

        }) {
            Text(text = "Minimal Alert Dialog ")

        }


        TextButton(onClick = {

            showBeautifulDialog = true

        }) {
            Text(text = "Beautiful Alert Dialog ")

        }

        TextButton(onClick = {

            showBasicDialog = true

        }) {
            Text(text = "Basic Alert Dialog")
        }

        TextButton(onClick = {
            showFullScreenDialog = true


        }) {
            Text(text = "Full Screen Alert Dialog")
        }


        TextButton(onClick = {

            showBottomSheetDialog = true

        }) {
            Text(text = "Show Bottom Sheet Dialog")
        }

        TextButton(onClick = {
            showBottomSheetFullScreenDialog = true


        }) {
            Text(text = "Show Full Screen Bottom Sheet Dialog")
        }


    }

}



