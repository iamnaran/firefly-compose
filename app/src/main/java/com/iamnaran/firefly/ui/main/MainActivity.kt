package com.iamnaran.firefly.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.navigation.NavGraph
import com.iamnaran.firefly.ui.auth.login.LoginScreen
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = AppLog.tagFor(this.javaClass)
    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    private var isAuthenticated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        isAuthenticated = mainViewModel.isUserAuthenticated()
        setContent {
            FireflyComposeTheme {
                navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val topBarState = rememberSaveable { (mutableStateOf(true)) }

                // Control TopBar and BottomBar
                when (navBackStackEntry?.destination?.route) {
                    AppScreen.Main.Home.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    AppScreen.Main.Profile.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    AppScreen.Main.Notification.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    else -> {
                        bottomBarState.value = false
                        topBarState.value = false
                    }
                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    bottomBar = {
                        if (bottomBarState.value) {
                            BottomBar(navController = navController)
                        }
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        NavGraph(
                            isAuthenticated,
                            navHostController = navController
                        )
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        LoginScreen(
            navigateToHome = {},
            navigateToSignUp = {})
    }
}

//https://gist.github.com/stevdza-san/5743a32dc1ec2d8eaf62c29d9bab1c43