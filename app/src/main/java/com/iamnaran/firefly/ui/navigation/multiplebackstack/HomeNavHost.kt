package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetailScreen
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.utils.AppLog

@Composable
fun HomeNavHost(homeNavHostController: NavHostController) {

    NavHost(navController = homeNavHostController, startDestination = AppScreen.Main.Home.route){

        composable(
            route = AppScreen.Main.Home.route) {
            HomeScreen(onProductClick = {
                val route = AppScreen.Main.ProductDetail.createRoute(productId = it)
                homeNavHostController.navigate(route)
            })
        }

        composable(
            route = AppScreen.Main.ProductDetail.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {

            // val productId = backStackEntry.arguments?.getString("productId")
            // value also can be  retrieve directly from responsible view-model
            ProductDetailScreen() {
                homeNavHostController.navigateUp()
            }
        }

    }

}