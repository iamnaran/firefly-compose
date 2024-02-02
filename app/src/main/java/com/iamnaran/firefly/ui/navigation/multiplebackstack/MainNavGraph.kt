package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.utils.AppLog

fun NavGraphBuilder.mainBackStackNavGraph(
    rootNavController: NavHostController
) {
    AppLog.showLog("Multiple BackStack Main NavGraph Setup")

    navigation(
        route = AppScreen.Main.route,
        startDestination = AppScreen.Main.Home.route
    ) {

        composable(route = AppScreen.Main.Home.route) {
            HomeNavHost()
        }

        composable(
            route = AppScreen.Main.Notification.route
        ) {
            NotificationNavHost()
        }

        composable(
            route = AppScreen.Main.Profile.route
        ) {
            ProfileNavHost(rootNavController)
        }

    }

}