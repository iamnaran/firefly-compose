package com.iamnaran.firefly.ui.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.ui.main.profile.component.ProfileCard
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {

    val user = profileViewModel.profileState.collectAsStateLifecycleAware()
    ProfileContent(user.value.userEntityDetails)

}

@Composable
fun ProfileContent(userEntity: UserEntity?) {

    Column(
        Modifier
            .background(Color.White)
            .padding(MaterialTheme.dimens.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (userEntity != null) {
            ProfileCard(userEntity)
        }

    }
}

