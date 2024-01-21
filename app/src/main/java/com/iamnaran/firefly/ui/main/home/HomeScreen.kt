package com.iamnaran.firefly.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.home.components.ProductLazyList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val homeState by viewModel.homeState.collectAsState()

    val loginStatus by viewModel.loginStatus.collectAsState()


    HomeContent(homeState) {
        navigateToLogin()
    }


}

@Composable
fun HomeContent(homeState: HomeState, onLogoutClick: () -> Unit) {

    Column {
        Text(text = "Home")
        ProductLazyList(homeState.allProducts)
    }

}

