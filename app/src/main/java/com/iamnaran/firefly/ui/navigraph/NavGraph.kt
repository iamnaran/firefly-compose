package com.iamnaran.firefly.ui.navigraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamnaran.firefly.ui.home.HomeScreen
import com.iamnaran.firefly.ui.login.LoginScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navHostController)
        }

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
    }
}