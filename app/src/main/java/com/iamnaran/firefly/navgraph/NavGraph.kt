package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamnaran.firefly.ui.auth.login.Login
import com.iamnaran.firefly.ui.auth.signup.SignUp
import com.iamnaran.firefly.ui.home.Home

@Composable
fun SetupNavGraph(initialRoute: String, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = initialRoute
    ) {
        composable(
            route = AppScreen.Login.route
        ) {
            Login(
                navigateToHome = {
                    navHostController.navigate(AppScreen.Home.route)
                },
                navigateToSignUp = {
                    navHostController.navigate(AppScreen.SignUp.route)
                },
            )
        }

        composable(
            route = AppScreen.SignUp.route
        ) {
            SignUp(onNavigateBack = {
                navHostController.navigateUp()
            })
        }

        composable(
            route = AppScreen.Home.route
        ) {
            Home(navigateToLogin = {})
        }
    }
}