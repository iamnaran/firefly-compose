package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.navigation.AppScreen

@Composable
fun NotificationNavHost() {
    val profileNavController = rememberNavController()

    NavHost(navController = profileNavController, startDestination = AppScreen.Main.Notification.route){

        composable(
            route = AppScreen.Main.Notification.route
        ) {
            NotificationScreen(){

            }
        }

    }

}