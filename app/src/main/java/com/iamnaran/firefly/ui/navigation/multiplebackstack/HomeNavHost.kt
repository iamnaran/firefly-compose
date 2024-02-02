package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetail
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.utils.AppLog

@Composable
fun HomeNavHost() {
    val homeNavController = rememberNavController()

    NavHost(navController = homeNavController, startDestination = AppScreen.Main.Home.route){

        composable(
            route = AppScreen.Main.Home.route) {
            HomeScreen(onProductClick = {
                val route = AppScreen.Main.ProductDetail.createRoute(productId = it)
                AppLog.showLog("Router---> $route")
                homeNavController.navigate(route)
            })
        }

        composable(
            route = AppScreen.Main.ProductDetail.route
        ) {

            // val productId = backStackEntry.arguments?.getString("productId")
            // value also can be  retrieve directly from responsible view-model
            ProductDetail() {
                homeNavController.navigateUp()
            }
        }

    }

}