package com.iamnaran.firefly.navgraph

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.auth.login.Login
import com.iamnaran.firefly.ui.auth.signup.SignUp

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = AppScreen.Auth.Login.route,
        route = AppScreen.Auth.route
    ){
        composable(
            route = AppScreen.Auth.Login.route
        ) {
            Login(
                navigateToHome = {
                    navController.navigate(AppScreen.Main.route)
                },
                navigateToSignUp = {
                    navController.navigate(AppScreen.Auth.Register.route)
                },
            )
        }

        composable(
            route = AppScreen.Auth.Register.route
        ) {
            SignUp(onNavigateBack = {
                navController.navigateUp()
            })
        }
    }

}