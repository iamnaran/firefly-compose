package com.iamnaran.firefly.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.dashboard.home.HomeScreen
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.main.profile.ProfileScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        composable(
            route = AppScreen.Main.Home.route
        ) {
            HomeScreen(navigateToLogin = {
                navController.navigate(AppScreen.Auth.route) {
                    popUpTo(AppScreen.Main.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = AppScreen.Main.Notification.route) {
            NotificationScreen()
        }

        composable(route = AppScreen.Main.Profile.route) {
            ProfileScreen()
        }

    }

}