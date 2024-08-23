package com.iamnaran.firefly.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.theme.AppIcons

private object Routes {

    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "signup"

    const val MAIN = "main"
    const val EXPLORE = "explore"
    const val INTEREST = "interest"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val NOTIFICATION = "notification"

    // details
    const val PRODUCT_DETAIL = "productDetail/{${ArgParams.PRODUCT_ID}}"
    const val RECIPE_DETAIL = "recipeDetail/{${ArgParams.RECIPE_ID}}"

}

private object ArgParams {

    const val PRODUCT_ID = "productId"
    const val RECIPE_ID = "recipeId"
    fun toPath(param: String) = "{${param}}"

}

sealed class AppScreen(val route: String) {
    object Auth : AppScreen(Routes.AUTH) {
        object Login : AppScreen(Routes.LOGIN)
        object Register : AppScreen(Routes.REGISTER)
    }


    object Main : TopLevelDestination(Routes.MAIN) {
        object Home : TopLevelDestination(
            route = Routes.HOME,
            title = R.string.home,
            selectedIcon = AppIcons.HomeFilled,
            unselectedIcon = AppIcons.HomeOutlined,
        ) {


        }

        object Notification : TopLevelDestination(
            route = Routes.NOTIFICATION,
            title = R.string.notification,
            selectedIcon = AppIcons.NotificationFilled,
            unselectedIcon = AppIcons.NotificationOutlined,
        ){

        }

        object Profile :
            TopLevelDestination(
                route = Routes.PROFILE,
                title = R.string.profile,
                selectedIcon = AppIcons.ProfileFilled,
                unselectedIcon = AppIcons.ProfileOutlined,
            ){

        }

        object Explore :  TopLevelDestination(
            route = Routes.EXPLORE,
            title = R.string.explore,
            selectedIcon = AppIcons.ExploredFilled,
            unselectedIcon = AppIcons.ExploredOutlined,
        )

        object Interest :  TopLevelDestination(
            route = Routes.INTEREST,
            title = R.string.interest,
            selectedIcon = AppIcons.InterestFilled,
            unselectedIcon = AppIcons.InterestOutlined,
        )


        object ProductDetail : TopLevelDestination(
            route = Routes.PRODUCT_DETAIL,
            navArguments = listOf(navArgument(ArgParams.PRODUCT_ID) {
                type = NavType.Companion.StringType
            })
        ) {
            fun createRoute(productId: String) =
                Routes.PRODUCT_DETAIL
                    .replace(ArgParams.toPath(ArgParams.PRODUCT_ID), productId
            )

            fun isProductDetailRoute(route: String): Boolean {
                // Define a regex pattern to match the productDetail
                val pattern = "productDetail.*".toRegex()
                return pattern.matches(route)
            }
        }


        object RecipeDetail : TopLevelDestination(
            route = Routes.RECIPE_DETAIL,
            navArguments = listOf(navArgument(ArgParams.RECIPE_ID) {
                type = NavType.Companion.StringType
            })
        ) {
            fun createRoute(recipeId: String) =
                Routes.RECIPE_DETAIL
                    .replace(ArgParams.toPath(ArgParams.RECIPE_ID), recipeId
                    )

            fun isRecipeDetailRoute(route: String): Boolean {
                // Define a regex pattern to match the productDetail
                val pattern = "recipeDetail.*".toRegex()
                return pattern.matches(route)
            }
        }

    }

}

sealed class TopLevelDestination(
    val route: String,
    val title: Int? = null,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val navArguments: List<NamedNavArgument> = emptyList()
)
