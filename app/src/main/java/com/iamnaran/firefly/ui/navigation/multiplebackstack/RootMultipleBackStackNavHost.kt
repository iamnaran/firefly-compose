package com.iamnaran.firefly.ui.navigation.multiplebackstack

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.ui.navigation.authNavGraph
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.topappbar.AppTopBar
import com.iamnaran.firefly.utils.AppLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootMultipleBackStackNavHost(
    isAuthenticated: Boolean,
) {
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)
    val snackbarHostState = remember { SnackbarHostState() }

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

    val rootNavHostController = rememberNavController()
    val homeNavHostController = rememberNavController()
    val notificationNavHostController = rememberNavController()

    val rootNavBackStackEntry by rootNavHostController.currentBackStackEntryAsState()
    val homeNavBackStackEntry by homeNavHostController.currentBackStackEntryAsState()
    val notificationNavBackStackEntry by notificationNavHostController.currentBackStackEntryAsState()

    val rootCurrentRoute = rootNavBackStackEntry?.destination?.route
    val homeCurrentRoute = homeNavBackStackEntry?.destination?.route
    val notificationCurrentRoute = notificationNavBackStackEntry?.destination?.route

    // Control TopBar and BottomBar
    rootCurrentRoute?.let {
        when (rootCurrentRoute) {
            AppScreen.Main.Home.route -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(AppScreen.Main.Home.title!!)

            }

            AppScreen.Main.Profile.route -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(AppScreen.Main.Profile.title!!)

            }

            AppScreen.Main.Notification.route -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(AppScreen.Main.Notification.title!!)
            }

            else -> {
                showBottomBarState.value = false
                showTopBarState.value = false
            }
        }

    }

    homeCurrentRoute?.let {
        if (AppScreen.Main.ProductDetail.isProductDetailRoute(it)) {
            showTopBarState.value = false
            showBottomBarState.value = true
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(barScrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            AppLog.showLog("TOP-BAR --> " + showTopBarState.value)
            if (showTopBarState.value) {
                AppTopBar(topAppbarTitle.value,
                    barScrollBehavior,
                    onActionCameraClick = {
                        rootNavHostController.navigate(AppScreen.Main.ArCamera.route)
                    }
                )
            } else {
                Box {

                }
            }
        },
        bottomBar = {
            if (showBottomBarState.value) {
                BottomBar(navController = rootNavHostController)
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavHost(
                navController = rootNavHostController,
                startDestination = if (isAuthenticated) AppScreen.Main.route else AppScreen.Auth.route,
                enterTransition = {
                    // you can change whatever you want transition
                    EnterTransition.None
                },
                exitTransition = {
                    // you can change whatever you want transition
                    ExitTransition.None
                }
            ) {

                authNavGraph(rootNavHostController)
                mainBackStackNavGraph(
                    rootNavHostController,
                    homeNavHostController,
                    notificationNavHostController,
                )
            }
        }
    }


}