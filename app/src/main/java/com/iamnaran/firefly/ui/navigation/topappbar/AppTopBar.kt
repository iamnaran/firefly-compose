package com.iamnaran.firefly.ui.navigation.topappbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import com.iamnaran.firefly.ui.theme.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    toolbarTitle: String,
    barScrollBehavior: TopAppBarScrollBehavior,
    onActionCameraClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        scrollBehavior = barScrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(toolbarTitle, style = MaterialTheme.typography.titleMedium)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(
                    imageVector = AppIcons.Menu,
                    contentDescription = "menu items"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onActionCameraClick()
            }) {
                Icon(
                    imageVector = AppIcons.SettingOutlined,
                    contentDescription = "Settings",
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.onSecondary),
    )
}