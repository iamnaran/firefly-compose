package com.iamnaran.firefly.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductScreen
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.main.profile.ProfileScreen
import com.iamnaran.firefly.utils.AppLog

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {
    AppLog.showLog("Main NavGraph Setup")
    navigation(
        startDestination = AppScreen.Main.Home.route,
        route = AppScreen.Main.route
    ) {
        composable(
            route = AppScreen.Main.Home.route,
            enterTransition = {
                return@composable fadeIn(tween(1000))
            }, exitTransition = {
                return@composable fadeOut(tween(700))
            }, popEnterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            }
        ) {
            HomeScreen(onProductClick = {
                navController.navigate(
                    AppScreen.Main.Product.createRoute(
                        productId = it
                    )
                )
            })
        }

        composable(route = AppScreen.Main.Notification.route) {
            NotificationScreen()
        }

        composable(route = AppScreen.Main.Profile.route) {
            ProfileScreen(navigateToLogin = {
                navController.navigate(AppScreen.Auth.route) {
                    popUpTo(AppScreen.Main.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(
            route = AppScreen.Main.Product.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            },
        ) {
             // val productId = backStackEntry.arguments?.getString("productId")
            // value also can be  retrieve directly from responsible view-model
            ProductScreen() {

            }
        }

    }

}