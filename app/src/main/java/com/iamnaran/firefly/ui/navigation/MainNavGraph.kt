package com.iamnaran.firefly.ui.navigation

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.main.explore.ExploreScreen
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetailScreen
import com.iamnaran.firefly.ui.main.interest.InterestScreen
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.main.notification.recipe.recipedetail.RecipeDetailScreen
import com.iamnaran.firefly.ui.main.profile.ProfileScreen
import com.iamnaran.firefly.ui.main.settings.SettingScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    rootNavBackStackEntry: NavBackStackEntry?
) {

    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        composable(
            route = AppScreen.Main.Home.route,
        ) {
            HomeScreen(
                onProductClick = {
                    val route = AppScreen.Main.ProductDetail.createRoute(productId = it)
                    navController.navigate(route)
                }
            )
        }


        composable(
            route = AppScreen.Main.Notification.route,
        ) {
            NotificationScreen() {
                val route = AppScreen.Main.RecipeDetail.createRoute(recipeId = it)
                navController.navigate(route)
            }
        }

        composable(
            route = AppScreen.Main.Profile.route,
        ) {
            ProfileScreen(navigateToLogin = {
                navController.navigate(AppScreen.Auth.route) {
                    popUpTo(AppScreen.Main.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = AppScreen.Main.Explore.route,
        ) {
            ExploreScreen() {

            }
        }


        composable(
            route = AppScreen.Main.Interest.route,
        ) {
            InterestScreen() {

            }
        }

        composable(
            route = AppScreen.Main.ProductDetail.route,
        ) {

            // value also can be  retrieve directly from responsible view-model
            val productId = rootNavBackStackEntry?.arguments?.getString("productId")
            if (productId != null) {
                ProductDetailScreen(
                    productId = productId
                ) {
                    navController.navigateUp()
                }
            }
        }


        dialog(
            route = AppScreen.Main.Settings.route,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)

        ) {
            SettingScreen {
                navController.navigateUp()
            }
        }

        dialog(
            route = AppScreen.Main.RecipeDetail.route,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)

        ) {

            val recipeId = rootNavBackStackEntry?.arguments?.getString("recipeId")
            if (recipeId != null) {
                RecipeDetailScreen(recipeId = recipeId) {
                    navController.navigateUp()
                }
            }


        }

    }

}