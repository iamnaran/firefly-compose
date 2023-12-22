/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iamnaran.firefly.ui.navigraph

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

data class ReplyTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class ReplyNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: ReplyTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

//val TOP_LEVEL_DESTINATIONS = listOf(
//    ReplyTopLevelDestination(
//        route = Route.HOME,
//        selectedIcon = Icons.Default.Home,
//        unselectedIcon = Icons.Default.Home,
//        iconTextId = R.string.tab_home
//    ),
//    ReplyTopLevelDestination(
//        route = Route.ARTICLES,
//        selectedIcon = Icons.Default.Build,
//        unselectedIcon = Icons.Default.Build,
//        iconTextId = R.string.tab_article
//    ),
//    ReplyTopLevelDestination(
//        route = Route.DM,
//        selectedIcon = Icons.Default.Menu,
//        unselectedIcon = Icons.Default.Menu,
//        iconTextId = R.string.tab_DM
//    ),
//    ReplyTopLevelDestination(
//        route = Route.GROUPS,
//        selectedIcon = Icons.Default.Person,
//        unselectedIcon = Icons.Default.Person,
//        iconTextId = R.string.tab_groups
//    )
//
//)
