package com.iamnaran.firefly.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.appcomponent.AppIcons

private object AppRoute {

    const val AUTH = "AUTH"
    const val LOGIN = "LOGIN"
    const val REGISTER = "SIGNUP"

    const val MAIN = "MAIN"
    const val HOME = "HOME"
    const val PROFILE = "PROFILE"
    const val NOTIFICATION = "NOTIFICATION"

    // details
    const val PRODUCT = "PRODUCT"

}

private object AppArgument {

    const val ARG_PRODUCT_ID = "productId"


}

sealed class AppScreen(val route: String) {
    object Auth : AppScreen(AppRoute.AUTH) {
        object Login : AppScreen(AppRoute.LOGIN)
        object Register : AppScreen(AppRoute.REGISTER)
    }


    object Main : TopLevelDestination(AppRoute.MAIN) {
        object Home : TopLevelDestination(
            route = AppRoute.HOME,
            title = R.string.home,
            selectedIcon = AppIcons.HomeFilled,
            unselectedIcon = AppIcons.HomeOutlined,
        ) {

        }

        object Profile :
            TopLevelDestination(
                route = AppRoute.PROFILE,
                title = R.string.profile,
                selectedIcon = AppIcons.ProfileFilled,
                unselectedIcon = AppIcons.ProfileOutlined,
            )

        object Notification : TopLevelDestination(
            route = AppRoute.NOTIFICATION,
            title = R.string.notification,
            selectedIcon = AppIcons.NotificationFilled,
            unselectedIcon = AppIcons.NotificationOutlined,
        )


        object Product : TopLevelDestination(
            AppRoute.PRODUCT + "/{productId}",
            navArguments = listOf(navArgument("productId") {
                type = NavType.StringType
            })
        ) {
            fun createRoute(productId: String) = AppRoute.PRODUCT + "/${productId}"
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
