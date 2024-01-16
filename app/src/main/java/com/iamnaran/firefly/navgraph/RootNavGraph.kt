package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(initialRoute: String, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = initialRoute
    ) {

        authNavGraph(navHostController)
        mainNavGraph(navHostController)

    }
}