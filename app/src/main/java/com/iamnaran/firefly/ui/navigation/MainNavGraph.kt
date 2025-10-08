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
import kotlinx.serialization.Serializable


@Serializable data object MainGraphRoute
@Serializable data object HomeRoute

@Serializable data object NotificationRoute

@Serializable data object ProfileRoute

@Serializable data object ExploreRoute

@Serializable data object InterestRoute

@Serializable data object SettingsRoute

@Serializable data class ProductDetailRoute(val productId: String)
@Serializable data class RecipeDetailRoute(val recipeId: String)

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
) {

    navigation<MainGraphRoute>(
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onProductClick = {
                    val route = ProductDetailRoute(productId = it)
                    navController.navigate(route)
                }
            )
        }

        composable<NotificationRoute> {
            NotificationScreen {
                val route = RecipeDetailRoute(recipeId = it)
                navController.navigate(route)
            }
        }

        composable<ProductDetailRoute> {
            ProfileScreen(navigateToLogin = {
                navController.navigate(AuthGraphRoot) {
                    popUpTo(MainGraphRoute) {
                        inclusive = true
                    }
                }
            })
        }

        composable<ExploreRoute> {
            ExploreScreen {
            }
        }

        composable<InterestRoute> {
            InterestScreen {
            }
        }

        composable<ProductDetailRoute> { backStackEntry ->
            val productDetail = backStackEntry.toRoute<ProductDetailRoute>()
            ProductDetailScreen(
                productId = productDetail.productId
            ) {
                navController.navigateUp()
            }
        }

        composable<ProfileRoute> {
            ProfileScreen(navigateToLogin = {
                navController.navigate(AuthGraphRoot) {
                    popUpTo(MainGraphRoute) {
                        inclusive = true
                    }
                }
            })
        }

        dialog<SettingsRoute>(
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            SettingScreen {
                navController.navigateUp()
            }
        }

        dialog<RecipeDetailRoute>(
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
        ) { backStackEntry ->
            val recipeDetail = backStackEntry.toRoute<RecipeDetailRoute>()
            RecipeDetailScreen(recipeId = recipeDetail.recipeId) {
                navController.navigateUp()
            }
        }
    }
}