package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.main.notification.recipe.recipedetail.RecipeDetailScreen
import com.iamnaran.firefly.ui.navigation.AppScreen

@Composable
fun NotificationNavHost(notificationNavHostController: NavHostController) {

    val notificationNavBackStackEntry by notificationNavHostController.currentBackStackEntryAsState()

    NavHost(navController = notificationNavHostController,
        startDestination = AppScreen.Main.Notification.route,
        enterTransition = {
            // you can change whatever you want transition
            EnterTransition.None
        },
        exitTransition = {
            // you can change whatever you want transition
            ExitTransition.None
        }
        ){

        composable(
            route = AppScreen.Main.Notification.route
        ) {
            NotificationScreen(){
                val route = AppScreen.Main.RecipeDetail.createRoute(recipeId = it)
                notificationNavHostController.navigate(route)
            }

        }

        composable(
            route = AppScreen.Main.RecipeDetail.route
        ) {

            val recipeId = notificationNavBackStackEntry?.arguments?.getString("recipeId")
            // value also can be  retrieve directly from responsible view-model
            RecipeDetailScreen(recipeId = recipeId) {
                notificationNavHostController.navigateUp()
            }
        }

    }

}