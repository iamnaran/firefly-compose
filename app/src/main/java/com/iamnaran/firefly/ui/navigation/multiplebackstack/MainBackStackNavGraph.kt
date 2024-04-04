package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.navigation.AppScreen

fun NavGraphBuilder.mainBackStackNavGraph(
    rootNavController: NavHostController,
    homeNavHostController: NavHostController,
    notificationNavHostController: NavHostController,


) {
    navigation(
        route = AppScreen.Main.route,
        startDestination = AppScreen.Main.Home.route
    ) {

        composable(route = AppScreen.Main.Home.route) {
            HomeNavHost(homeNavHostController)
        }

        composable(
            route = AppScreen.Main.Notification.route
        ) {
            NotificationNavHost(notificationNavHostController)
        }

        composable(
            route = AppScreen.Main.Profile.route
        ) {
            ProfileNavHost(rootNavController)
        }

    }

}