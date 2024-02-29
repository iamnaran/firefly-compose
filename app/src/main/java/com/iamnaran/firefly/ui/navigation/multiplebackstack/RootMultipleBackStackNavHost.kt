package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.ui.navigation.authNavGraph
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.topappbar.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootMultipleBackStackNavHost(
    topBarState: MutableState<Boolean>,
    bottomBarState: MutableState<Boolean>,
    barScrollBehavior: TopAppBarScrollBehavior,
    topAppbarTitle: MutableState<String>,
    snackbarHostState: SnackbarHostState,
    isAuthenticated: Boolean,
    rootNavHostController: NavHostController
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(barScrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            if (topBarState.value) {
                AppTopBar(topAppbarTitle.value, barScrollBehavior) {
                    rootNavHostController.navigate(AppScreen.Main.ArCamera.route)
                }
            }
        },
        bottomBar = {
            if (bottomBarState.value) {
                BottomBar(navController = rootNavHostController)
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = rootNavHostController,
                startDestination = if (isAuthenticated) AppScreen.Main.route else AppScreen.Auth.route
            ) {

                authNavGraph(rootNavHostController)
                mainBackStackNavGraph(rootNavHostController)
            }
        }
    }


}