package com.iamnaran.firefly.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.navgraph.AppScreen
import com.iamnaran.firefly.navgraph.RootNavGraph
import com.iamnaran.firefly.ui.auth.login.Login
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
//        splashScreen.setKeepOnScreenCondition{}
        setContent {
            val initialRoute: String = AppScreen.Main.route
            val loginStatus: Boolean
            FireflyComposeTheme {
                navController = rememberNavController()
                RootNavGraph(initialRoute, navHostController = navController)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        Login(
            navigateToHome = {},
            navigateToSignUp = {})
    }
}