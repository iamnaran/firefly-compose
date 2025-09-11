package com.iamnaran.firefly.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.iamnaran.firefly.ui.navigation.RootNavHost
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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


@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainScreenContent(
    isAuthenticated: Boolean
) {

    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {

            RootNavHost(isAuthenticated)
        }


    }
}


/* Multiple Backstack
   Commented due to some on going issue in google issue tracker
   Check multiple backstack folder -> not in used for now
   RootMultipleBackStackNavHost(isAuthenticated)
*/


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        MainScreenContent(true)
    }
}