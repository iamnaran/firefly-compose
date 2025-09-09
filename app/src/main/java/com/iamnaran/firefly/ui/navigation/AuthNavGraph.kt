package com.iamnaran.firefly.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.auth.login.LoginScreen
import com.iamnaran.firefly.ui.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {


    navigation<FireflyScreen.AuthRoot>(
        startDestination = FireflyScreen.Login,
    ) {
        composable<FireflyScreen.Login> {
            LoginScreen(
                navigateToHome = {
                    navController.navigate(FireflyScreen.MainRoot) {
                        popUpTo(FireflyScreen.AuthRoot) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUp = {
                    navController.navigate(FireflyScreen.Register)
                },
            )
        }

        composable<FireflyScreen.Register> {
            SignUpScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }
    }

}