package com.iamnaran.firefly.ui.navgraph

private object Route {
    const val LOGIN = "LOGIN"
    const val SIGNUP = "SIGNUP"
    const val MAIN = "MAIN"
    const val HOME = "HOME"
    const val MESSAGE = "MESSAGE"
    const val ARTICLES = "ARTICLES"
    const val DM = "DM"
    const val GROUPS = "Groups"

}

sealed class Screen (val route: String){
    object Login: Screen(Route.LOGIN)
    object SignUp: Screen(Route.SIGNUP)
    object Home: Screen(Route.HOME)
}