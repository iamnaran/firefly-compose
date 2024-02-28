package com.iamnaran.firefly.ui.main.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.notification.herocard.RecipeHeroScreen

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
    onRecipeClick: (String) -> Unit,
) {

    val notificationState by viewModel.notificationState.collectAsState()

    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NotificationScreenContent(notificationState, onRecipeClick)

    }

}

@Composable
fun NotificationScreenContent(
    notificationState: NotificationState,
    onRecipeClick: (String) -> Unit
) {

    RecipeHeroScreen(notificationState = notificationState, onRecipeClick = onRecipeClick)

}

