package com.iamnaran.firefly.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.navigraph.SetupNavGraph
import com.iamnaran.firefly.ui.login.LoginScreen
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireflyComposeTheme {
                navController = rememberNavController()
                SetupNavGraph(navHostController = navController)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        val navHostController = rememberNavController()
        LoginScreen(navHostController = navHostController)
    }
}