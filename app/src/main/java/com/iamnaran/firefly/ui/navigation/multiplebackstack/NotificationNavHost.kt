package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.navigation.AppScreen

@Composable
fun NotificationNavHost(profileNavHostController: NavHostController) {
    NavHost(navController = profileNavHostController, startDestination = AppScreen.Main.Notification.route){

        composable(
            route = AppScreen.Main.Notification.route
        ) {
            NotificationScreen(){

            }
        }

    }

}