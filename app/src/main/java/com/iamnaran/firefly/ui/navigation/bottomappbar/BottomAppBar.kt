package com.iamnaran.firefly.ui.navigation.bottomappbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamnaran.firefly.ui.navigation.BottomNavigationScreen

@Composable
fun BottomAppBar(
    navController: NavHostController, state: MutableState<Boolean>, modifier: Modifier = Modifier
) {
    val navigationScreen = listOf(
        BottomNavigationScreen.Home, BottomNavigationScreen.Notification, BottomNavigationScreen.Profile
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color.Gray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationScreen.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,

                label = {
                        Text(text = screen.title.toString())
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = "")
                },
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Color.White
                ),
            )
        }
    }

}