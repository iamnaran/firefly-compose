package com.iamnaran.firefly.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.dashboard.home.Home

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
            Home(navigateToLogin = {
                navController.navigate(AppScreen.Auth.route){
                    popUpTo(AppScreen.Main.route){
                        inclusive = true
                    }
                }
            })
        }
    }

}