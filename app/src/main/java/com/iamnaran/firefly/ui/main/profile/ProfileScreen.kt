package com.iamnaran.firefly.ui.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.ui.main.interest.components.dialogs.CustomAlertDialog
import com.iamnaran.firefly.ui.main.profile.component.ProfileCard
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val openAlertDialog = remember { mutableStateOf(false) }

    val user = profileViewModel.profileState.collectAsStateLifecycleAware()
    user.value.userEntityDetails?.let {
        ProfileContent(it, openAlertDialog) {
            openAlertDialog.value = true
        }
    }

    when {
        openAlertDialog.value -> {
            CustomAlertDialog(
                dialogTitle = "Logout Confirmation",
                dialogSubTitle = "Are you sure you want to logout? All caches data will be cleared.",
                onDismissRequest = {
                    openAlertDialog.value = false
                }) {
                navigateToLogin()
                profileViewModel.doLogout()
            }
        }
    }
}

@Composable
fun ProfileContent(
    userEntity: UserEntity,
    openAlertDialog: MutableState<Boolean>,
    onLogoutClick: () -> Unit
) {

    Column(
        Modifier
            .padding(MaterialTheme.dimens.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileCard(userEntity)

        Button(
            onClick = {

                onLogoutClick()

            }, Modifier
                .padding(MaterialTheme.dimens.regular)
        ) {
            Text(text = "Logout")

        }


    }
}

