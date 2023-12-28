package com.iamnaran.firefly.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.home.components.ProductLazyList

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val homeState by viewModel.homeState.collectAsState()

    HomeContent(homeState) {
        navigateToLogin()
    }


}

@Composable
fun HomeContent(homeState: HomeState, onLogoutClick: () -> Unit) {

    ProductLazyList(homeState.allProducts)

}

