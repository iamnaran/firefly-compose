package com.iamnaran.firefly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.iamnaran.firefly.utils.AppLog

@Composable
fun RootNavHost(isLoggedIn: Boolean, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination =  if(isLoggedIn) AppScreen.Main.route else AppScreen.Auth.route
    ) {
        AppLog.showLog("Nav Graph Setup")

        authNavGraph(navHostController)
        mainNavGraph(navHostController)

    }
}