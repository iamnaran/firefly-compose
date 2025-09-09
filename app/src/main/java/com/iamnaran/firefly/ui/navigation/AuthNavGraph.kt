package com.iamnaran.firefly.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.iamnaran.firefly.ui.auth.login.LoginScreen
import com.iamnaran.firefly.ui.auth.signup.SignUpScreen
import kotlinx.serialization.Serializable


@Serializable data object AuthGraphRoot
@Serializable data object LoginRoute
@Serializable data object RegisterRoute
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {


    navigation<AuthGraphRoot>(
        startDestination = LoginRoute,
    ) {
        composable<LoginRoute> {
            LoginScreen(
                navigateToHome = {
                    navController.navigate(MainGraphRoute) {
                        popUpTo(AuthGraphRoot) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUp = {
                    navController.navigate(RegisterRoute)
                },
            )
        }

        composable<RegisterRoute> {
            SignUpScreen(onNavigateBack = {
                navController.navigateUp()
            })
        }
    }
}