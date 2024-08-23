package com.iamnaran.firefly.ui.navigation

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.topappbar.AppTopBar
import com.iamnaran.firefly.utils.AppLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(isAuthenticated: Boolean) {
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)
    val snackbarHostState = remember { SnackbarHostState() }

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

    // State composable used to hold the
    // value of the current active screen
    val currentScreen = remember { mutableStateOf(AppScreen.Main.Home.route) }

    val coroutineScope = rememberCoroutineScope()
    val rootNavHostController = rememberNavController()
    val rootNavBackStackEntry by rootNavHostController.currentBackStackEntryAsState()

    // Control TopBar and BottomBar
    when (rootNavBackStackEntry?.destination?.route) {
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

        AppScreen.Main.Explore.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = stringResource(AppScreen.Main.Explore.title!!)
        }


        AppScreen.Main.Interest.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = stringResource(AppScreen.Main.Interest.title!!)
        }

        AppScreen.Main.ProductDetail.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
        }

        AppScreen.Main.RecipeDetail.route -> {
            showBottomBarState.value = true
            showTopBarState.value = true
        }

        else -> {
            showBottomBarState.value = false
            showTopBarState.value = false
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
            modifier = Modifier
                .padding(paddingValues)
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
                mainNavGraph(rootNavHostController, rootNavBackStackEntry)
            }
        }
    }


}