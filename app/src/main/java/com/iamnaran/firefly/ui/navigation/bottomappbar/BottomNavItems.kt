package com.iamnaran.firefly.ui.navigation.bottomappbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.navigation.ExploreRoute
import com.iamnaran.firefly.ui.navigation.HomeRoute
import com.iamnaran.firefly.ui.navigation.InterestRoute
import com.iamnaran.firefly.ui.navigation.NotificationRoute
import com.iamnaran.firefly.ui.navigation.ProfileRoute
import com.iamnaran.firefly.ui.theme.AppIcons
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavItem<T>(
    val destination: T,
    val titleRes: Int,
    @Contextual val selectedIcon: ImageVector,
    @Contextual val unselectedIcon: ImageVector
) {
    @Serializable
    data object Home : BottomNavItem<HomeRoute>(
        HomeRoute,
        R.string.home,
        AppIcons.HomeFilled,
        AppIcons.HomeOutlined
    )

    @Serializable
    data object Explore : BottomNavItem<ExploreRoute>(
        ExploreRoute,
        R.string.explore,
        AppIcons.ExploredFilled,
        AppIcons.ExploredOutlined
    )

    @Serializable
    data object Interest : BottomNavItem<InterestRoute>(
        InterestRoute,
        R.string.interest,
        AppIcons.InterestFilled,
        AppIcons.InterestOutlined
    )

    @Serializable
    data object Notification : BottomNavItem<NotificationRoute>(
        NotificationRoute,
        R.string.notification,
        AppIcons.NotificationFilled,
        AppIcons.NotificationOutlined
    )

    @Serializable
    data object Profile : BottomNavItem<ProfileRoute>(
        ProfileRoute,
        R.string.profile,
        AppIcons.ProfileFilled,
        AppIcons.ProfileOutlined
    )
}

val bottomNavItemsList = listOf(
    BottomNavItem.Home,
    BottomNavItem.Explore,
    BottomNavItem.Interest,
    BottomNavItem.Notification,
    BottomNavItem.Profile
)