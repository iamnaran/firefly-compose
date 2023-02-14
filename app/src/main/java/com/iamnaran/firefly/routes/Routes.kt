package com.iamnaran.firefly.routes

sealed class Routes(val route: String) {
    object SignUp : Routes(RouteConstants.SignUpRoute)
    object ForgotPassword : Routes(RouteConstants.ForgotPasswordRoute)
    object Login : Routes(RouteConstants.LoginRoute)
    object Home : Routes(RouteConstants.HomeRoute)
}