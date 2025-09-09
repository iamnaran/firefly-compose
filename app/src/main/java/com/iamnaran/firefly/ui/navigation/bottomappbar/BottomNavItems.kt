package com.iamnaran.firefly.ui.navigation.bottomappbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.navigation.FireflyScreen
import com.iamnaran.firefly.ui.theme.AppIcons

data class BottomNavItem(
    val destination: FireflyScreen,
    val titleRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        FireflyScreen.Home,
        R.string.home,
        AppIcons.HomeFilled,
        AppIcons.HomeOutlined),
    BottomNavItem(
        FireflyScreen.Explore,
        R.string.explore,
        AppIcons.ExploredFilled,
        AppIcons.ExploredOutlined
    ),
    BottomNavItem(
        FireflyScreen.Interest,
        R.string.interest,
        AppIcons.InterestFilled,
        AppIcons.InterestOutlined
    ),
    BottomNavItem(
        FireflyScreen.Notification,
        R.string.notification,
        AppIcons.NotificationFilled,
        AppIcons.NotificationOutlined
    ),
    BottomNavItem(
        FireflyScreen.Profile,
        R.string.profile,
        AppIcons.ProfileFilled,
        AppIcons.ProfileOutlined
    ),
)