package com.iamnaran.firefly.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iamnaran.firefly.ui.navigation.RootNavHost
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG: String = AppLog.tagFor(this.javaClass)
    private val mainViewModel: MainViewModel by viewModels()
    private var isAuthenticated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        isAuthenticated = mainViewModel.isUserAuthenticated()
        setContent {
            FireflyComposeTheme {
                MainScreenContent(isAuthenticated)
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    isAuthenticated: Boolean
) {

    RootNavHost(isAuthenticated)

 /* Multiple Backstack
    Commented due to some on going issue in google issue tracker
    Check multiple backstack folder -> not in used for now
    RootMultipleBackStackNavHost(isAuthenticated)
 */



}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        MainScreenContent(true)
    }
}