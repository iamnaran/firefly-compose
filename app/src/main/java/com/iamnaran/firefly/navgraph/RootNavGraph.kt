package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(isLoggedIn: Boolean, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination =  if(isLoggedIn) AppScreen.Main.route else AppScreen.Auth.route
    ) {

        authNavGraph(navHostController)
        mainNavGraph(navHostController)

    }
}