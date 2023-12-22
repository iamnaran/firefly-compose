package com.iamnaran.firefly.ui.navigraph

private object Route {
    const val LOGIN = "LOGIN"
    const val MAIN = "MAIN"
    const val HOME = "HOME"
    const val MESSAGE = "MESSAGE"
    const val ARTICLES = "ARTICLES"
    const val DM = "DM"
    const val GROUPS = "Groups"

}

sealed class Screen (val route: String){
    object Login: Screen(Route.LOGIN)
    object Home: Screen(Route.HOME)
}