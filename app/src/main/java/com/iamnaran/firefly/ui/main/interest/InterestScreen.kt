package com.iamnaran.firefly.ui.main.interest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun InterestScreen(
    interestViewModel: InterestViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val user = interestViewModel.profileState.collectAsStateLifecycleAware()

    InterestContent() {

    }


}

@Composable
fun InterestContent(onLogoutClick: () -> Unit) {

    Column(
        Modifier
            .padding(MaterialTheme.dimens.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



    }
}

