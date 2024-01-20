package com.iamnaran.firefly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavGraph(isLoggedIn: Boolean, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination =  if(isLoggedIn) AppScreen.Main.route else AppScreen.Auth.route
    ) {

        authNavGraph(navHostController)
        mainNavGraph(navHostController)

    }
}