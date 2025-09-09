package com.iamnaran.firefly.ui.navigation.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.navigation.FireflyScreen
import com.iamnaran.firefly.ui.theme.AppIcons

@Composable
fun BottomBar(
    navController: NavHostController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.toRoute<FireflyScreen>()

    NavigationBar {
        bottomNavItems.forEach { screen ->
            val isSelected = currentDestination?.let { it::class == screen::class } == true

            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(
                        text = when (screen) {
                            FireflyScreen.Home -> stringResource(R.string.home)
                            FireflyScreen.Explore -> stringResource(R.string.explore)
                            FireflyScreen.Interest -> stringResource(R.string.interest)
                            FireflyScreen.Notification -> stringResource(R.string.notification)
                            FireflyScreen.Profile -> stringResource(R.string.profile)
                            else -> ""
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            FireflyScreen.Home -> if (isSelected) AppIcons.HomeFilled else AppIcons.HomeOutlined
                            FireflyScreen.Explore -> if (isSelected) AppIcons.ExploredFilled else AppIcons.ExploredOutlined
                            FireflyScreen.Interest -> if (isSelected) AppIcons.InterestFilled else AppIcons.InterestOutlined
                            FireflyScreen.Notification -> if (isSelected) AppIcons.NotificationFilled else AppIcons.NotificationOutlined
                            FireflyScreen.Profile -> if (isSelected) AppIcons.ProfileFilled else AppIcons.ProfileOutlined
                            else -> AppIcons.HomeOutlined
                        },
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(screen) {   // 🚀 type-safe navigation
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}