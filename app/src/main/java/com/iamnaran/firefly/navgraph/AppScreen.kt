package com.iamnaran.firefly.navgraph

private object AppRoute {
    const val LOGIN = "LOGIN"
    const val SIGNUP = "SIGNUP"
    const val MAIN = "MAIN"
    const val HOME = "HOME"
    const val MESSAGE = "MESSAGE"
    const val ARTICLES = "ARTICLES"
    const val DM = "DM"
    const val GROUPS = "Groups"

}

sealed class AppScreen (val route: String){
    object Login: AppScreen(AppRoute.LOGIN)
    object SignUp: AppScreen(AppRoute.SIGNUP)
    object Home: AppScreen(AppRoute.HOME)
}