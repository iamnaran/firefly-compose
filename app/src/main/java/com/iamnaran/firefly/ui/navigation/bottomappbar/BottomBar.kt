package com.iamnaran.firefly.ui.navigation.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.navigation.ExploreRoute
import com.iamnaran.firefly.ui.navigation.HomeRoute
import com.iamnaran.firefly.ui.navigation.InterestRoute
import com.iamnaran.firefly.ui.navigation.NotificationRoute
import com.iamnaran.firefly.ui.navigation.ProfileRoute
import com.iamnaran.firefly.ui.theme.AppIcons
import com.iamnaran.firefly.utils.AppLog

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val navDestinationScreens = remember {
        bottomNavItemsList
    }

    NavigationBar {

        navDestinationScreens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.destination::class.qualifiedName } == true
            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(
                        text = when (screen.destination) {
                            HomeRoute -> stringResource(R.string.home)
                            ExploreRoute -> stringResource(R.string.explore)
                            InterestRoute -> stringResource(R.string.interest)
                            NotificationRoute -> stringResource(R.string.notification)
                            ProfileRoute -> stringResource(R.string.profile)
                            else -> ""
                        },
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = {
                    Icon(
                        imageVector = when (screen.destination) {
                            HomeRoute -> if (isSelected) AppIcons.HomeFilled else AppIcons.HomeOutlined
                            ExploreRoute -> if (isSelected) AppIcons.ExploredFilled else AppIcons.ExploredOutlined
                            InterestRoute -> if (isSelected) AppIcons.InterestFilled else AppIcons.InterestOutlined
                            NotificationRoute -> if (isSelected) AppIcons.NotificationFilled else AppIcons.NotificationOutlined
                            ProfileRoute -> if (isSelected) AppIcons.ProfileFilled else AppIcons.ProfileOutlined

                            else -> AppIcons.HomeOutlined
                        },
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(screen.destination) {
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