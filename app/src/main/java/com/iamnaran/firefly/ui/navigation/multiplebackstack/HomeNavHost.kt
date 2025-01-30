package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetailScreen
import com.iamnaran.firefly.ui.navigation.AppScreen

@Composable
fun HomeNavHost(homeNavHostController: NavHostController) {

    val homeNavBackStackEntry by homeNavHostController.currentBackStackEntryAsState()


    NavHost(navController = homeNavHostController,
        startDestination = AppScreen.Main.Home.route,
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
            route = AppScreen.Main.Home.route
        ) {
//            HomeScreen(onProductClick = {
//                val route = AppScreen.Main.ProductDetail.createRoute(productId = it)
//                homeNavHostController.navigate(route)
//            })
        }

        composable(
            route = AppScreen.Main.ProductDetail.route
        ) {


        }

    }

}