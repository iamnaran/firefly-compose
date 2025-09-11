package com.iamnaran.firefly.ui.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.appcomponent.snackbar.ObserveAsEvents
import com.iamnaran.firefly.ui.appcomponent.snackbar.SnackbarManager
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.topappbar.AppTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavHost(isAuthenticated: Boolean) {
    SharedTransitionLayout {

        val topAppbarTitle = remember { mutableStateOf("") }
        val topAppBarState = rememberTopAppBarState()
        val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)
        val snackbarHostState = remember { SnackbarHostState() }

        val showBottomBarState = rememberSaveable { (mutableStateOf(true)) }
        val showTopBarState = rememberSaveable { (mutableStateOf(true)) }

        val coroutineScope = rememberCoroutineScope()
        val rootNavHostController = rememberNavController()
        val rootNavBackStackEntry by rootNavHostController.currentBackStackEntryAsState()


        ObserveAsEvents(
            flow = SnackbarManager.events,
            snackbarHostState
        ) { event ->
            coroutineScope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()

                val result = snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = SnackbarDuration.Short
                )

                if (result == SnackbarResult.ActionPerformed) {
                    event.action?.action?.invoke()
                }
            }
        }


        // Control TopBar and BottomBar
        when (rootNavBackStackEntry?.destination?.route) {
            HomeRoute::class.qualifiedName-> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(R.string.home)
            }

            NotificationRoute::class.qualifiedName -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(R.string.notification)
            }
            ExploreRoute::class.qualifiedName -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(R.string.explore)
            }
            InterestRoute::class.qualifiedName -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(R.string.interest)
            }
            ProfileRoute::class.qualifiedName -> {
                showBottomBarState.value = true
                showTopBarState.value = true
                topAppbarTitle.value = stringResource(R.string.profile)
            }
            ProductDetailRoute::class.qualifiedName,
            RecipeDetailRoute::class.qualifiedName -> {
                showBottomBarState.value = false
                showTopBarState.value = false
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
                if (showTopBarState.value) {
                    AppTopBar(topAppbarTitle.value,
                        barScrollBehavior,
                        onActionCameraClick = {
                            rootNavHostController.navigate(SettingsRoute)
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
                    startDestination = if (isAuthenticated) MainGraphRoute else AuthGraphRoot,
                    enterTransition = {
                        EnterTransition.None
                    },
                    exitTransition = {
                        ExitTransition.None
                    }
                ) {

                    authNavGraph(
                        rootNavHostController
                    )
                    mainNavGraph(
                        rootNavHostController
                    )
                }
            }
        }

    }


}

