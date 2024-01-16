package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AppScreen.Main.route
    ) {

        authNavGraph(navHostController)
        mainNavGraph(navHostController)

    }
}