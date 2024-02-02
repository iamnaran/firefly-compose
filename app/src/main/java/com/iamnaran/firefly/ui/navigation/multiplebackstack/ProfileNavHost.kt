package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.main.profile.ProfileScreen
import com.iamnaran.firefly.ui.navigation.AppScreen

@Composable
fun ProfileNavHost(rootNavController: NavHostController) {
    val profileNavController = rememberNavController()

    NavHost(navController = profileNavController, startDestination = AppScreen.Main.Profile.route) {

        composable(
            route = AppScreen.Main.Profile.route
        ) {
            ProfileScreen(navigateToLogin = {
                rootNavController.navigate(AppScreen.Auth.route) {
                    popUpTo(AppScreen.Main.route) {
                        inclusive = true
                    }
                }
            })
        }

    }

}