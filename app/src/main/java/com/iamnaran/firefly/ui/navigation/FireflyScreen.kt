package com.iamnaran.firefly.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class FireflyScreen {
    @Serializable object AuthRoot : FireflyScreen()
    @Serializable object Login : FireflyScreen()
    @Serializable object Register : FireflyScreen()

    @Serializable object MainRoot : FireflyScreen()
    @Serializable object Home : FireflyScreen()
    @Serializable object Notification : FireflyScreen()
    @Serializable object Profile : FireflyScreen()
    @Serializable object Explore : FireflyScreen()
    @Serializable object Interest : FireflyScreen()
    @Serializable object Settings : FireflyScreen()

    @Serializable data class ProductDetail(val productId: String) : FireflyScreen()
    @Serializable data class RecipeDetail(val recipeId: String) : FireflyScreen()

}