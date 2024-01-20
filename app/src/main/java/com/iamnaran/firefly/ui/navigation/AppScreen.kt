package com.iamnaran.firefly.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
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

}

sealed class AppScreen(val route: String) {
    object Auth : AppScreen(AppRoute.AUTH) {
        object Login : AppScreen(AppRoute.LOGIN)
        object Register : AppScreen(AppRoute.REGISTER)
    }

    object Main : BottomNavigationScreen(AppRoute.MAIN) {
        object Home : BottomNavigationScreen(
            route = AppRoute.HOME,
            title = R.string.home,
            icon = AppIcons.Home
        )

        object Profile :
            BottomNavigationScreen(
                route = AppRoute.PROFILE,
                title = R.string.profile,
                icon = AppIcons.Profile
            )

        object Notification : BottomNavigationScreen(
            route = AppRoute.NOTIFICATION,
            title = R.string.notification,
            icon = AppIcons.Notification
        )
    }

}

sealed class BottomNavigationScreen(
    val route: String,
    val title: Int? = null,
    val icon: ImageVector? = null
)
