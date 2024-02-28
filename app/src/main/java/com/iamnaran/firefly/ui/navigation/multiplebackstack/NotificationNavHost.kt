package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetail
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