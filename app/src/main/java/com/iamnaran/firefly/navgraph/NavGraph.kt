package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamnaran.firefly.ui.auth.login.Login
import com.iamnaran.firefly.ui.auth.signup.SignUp
import com.iamnaran.firefly.ui.home.HomeScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            Login(
                navigateToHome = {
                    navHostController.navigate(Screen.Home.route)
                },
                navigateToSignUp = {
                    navHostController.navigate(Screen.SignUp.route)
                },
            )
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUp(onNavigateBack = {
                navHostController.navigateUp()
            })
        }

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen()
        }
    }
}