package com.iamnaran.firefly.navgraph

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.common.AppIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    MESSAGE(
        selectedIcon = AppIcons.Add,
        unselectedIcon = AppIcons.Add,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
    ),
    HOME(
        selectedIcon = AppIcons.Add,
        unselectedIcon = AppIcons.Add,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
    ),
    MENU(
        selectedIcon = AppIcons.Add,
        unselectedIcon = AppIcons.Add,
        iconTextId = R.string.app_name,
        titleTextId = R.string.app_name,
    ),
}