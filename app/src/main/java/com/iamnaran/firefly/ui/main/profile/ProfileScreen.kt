package com.iamnaran.firefly.ui.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.ui.main.profile.component.ProfileCard
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val user = profileViewModel.profileState.collectAsStateLifecycleAware()
    user.value.userEntityDetails?.let {
        ProfileContent(it) {
            navigateToLogin()
            profileViewModel.doLogout();
        }
    }

}

@Composable
fun ProfileContent(userEntity: UserEntity, onLogoutClick: () -> Unit) {

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

