package com.iamnaran.firefly.ui.navigation

import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.iamnaran.firefly.ui.main.explore.ExploreScreen
import com.iamnaran.firefly.ui.main.home.HomeScreen
import com.iamnaran.firefly.ui.main.home.productdetail.ProductDetailScreen
import com.iamnaran.firefly.ui.main.interest.InterestScreen
import com.iamnaran.firefly.ui.main.notification.NotificationScreen
import com.iamnaran.firefly.ui.main.notification.recipe.recipedetail.RecipeDetailScreen
import com.iamnaran.firefly.ui.main.profile.ProfileScreen
import com.iamnaran.firefly.ui.main.settings.SettingScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {

    navigation<FireflyScreen.MainRoot>(
        startDestination = FireflyScreen.Home,
    ) {
        composable<FireflyScreen.Home> {
            HomeScreen(
                onProductClick = {
                    val route = FireflyScreen.ProductDetail(productId = it)
                    navController.navigate(route)
                }
            )
        }


        composable<FireflyScreen.Notification> {
            NotificationScreen {
                val route = FireflyScreen.RecipeDetail(recipeId = it)
                navController.navigate(route)
            }
        }

        composable<FireflyScreen.ProductDetail> {
            ProfileScreen(navigateToLogin = {
                navController.navigate(FireflyScreen.AuthRoot) {
                    popUpTo(FireflyScreen.MainRoot) {
                        inclusive = true
                    }
                }
            })
        }

        composable<FireflyScreen.Explore> {
            ExploreScreen {

            }
        }


        composable<FireflyScreen.Interest> {
            InterestScreen {

            }
        }

        composable<FireflyScreen.ProductDetail> { backStackEntry ->
            val productDetail = backStackEntry.toRoute<FireflyScreen.ProductDetail>()
            ProductDetailScreen(
                productId = productDetail.productId
            ) {
                navController.navigateUp()
            }
        }


        dialog<FireflyScreen.Settings>(
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            SettingScreen {
                navController.navigateUp()
            }
        }

        dialog<FireflyScreen.RecipeDetail>(
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)

        ) { backStackEntry ->

            val recipeDetail = backStackEntry.toRoute<FireflyScreen.RecipeDetail>()

            RecipeDetailScreen(recipeId = recipeDetail.recipeId) {
                navController.navigateUp()
            }


        }

    }

}