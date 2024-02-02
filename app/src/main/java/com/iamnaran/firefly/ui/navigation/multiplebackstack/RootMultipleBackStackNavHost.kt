package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.ui.navigation.authNavGraph
import com.iamnaran.firefly.ui.navigation.mainNavGraph
import com.iamnaran.firefly.utils.AppLog

@Composable
fun RootMultipleBackStackNavHost(isLoggedIn: Boolean, rootNavHostController: NavHostController) {
    NavHost(
        navController = rootNavHostController,
        startDestination = if(isLoggedIn) AppScreen.Main.route else AppScreen.Auth.route
    ) {

        authNavGraph(rootNavHostController)
        mainBackStackNavGraph(rootNavHostController)
    }
}