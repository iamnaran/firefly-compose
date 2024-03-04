package com.iamnaran.firefly.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.multiplebackstack.mainBackStackNavGraph
import com.iamnaran.firefly.ui.navigation.topappbar.AppTopBar
import com.iamnaran.firefly.utils.AppLog
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost(isAuthenticated: Boolean) {
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)
    val snackbarHostState = remember { SnackbarHostState() }

    val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var drawerWidth by remember {
        mutableFloatStateOf(drawerState.offset.value)
    }
    val contentOffset = remember {
        derivedStateOf {
            drawerState.offset.value
        }
    }

    SideEffect {
        if (drawerWidth == 0f) {
            drawerWidth = drawerState.offset.value
        }
    }


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

    ModalNavigationDrawer(

        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(290.dp)
                    .fillMaxHeight()
            ) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = {

                    }
                )
            }
        },
        gesturesEnabled = true,
        drawerState = drawerState

    ) {
        val xPos = (abs(drawerWidth) - abs(contentOffset.value))




        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = with(LocalDensity.current) {
                    max(0.dp, xPos.toDp() - 56.dp)
                })
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


}