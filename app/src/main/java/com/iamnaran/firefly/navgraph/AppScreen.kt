package com.iamnaran.firefly.navgraph

private object AppRoute {

    const val AUTH = "AUTH"
    const val LOGIN = "LOGIN"
    const val REGISTER = "SIGNUP"

    const val MAIN = "MAIN"
    const val HOME = "HOME"


}

sealed class AppScreen (val route: String){

    object Auth : AppScreen(AppRoute.AUTH){
        object Login: AppScreen(AppRoute.LOGIN)
        object Register: AppScreen(AppRoute.REGISTER)

    }

    object Main : AppScreen(AppRoute.MAIN){
        object Home: AppScreen(AppRoute.HOME)
    }
}