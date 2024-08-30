package com.iamnaran.firefly.ui.main.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.ui.main.home.components.ProductItem
import com.iamnaran.firefly.ui.main.home.components.ProductLazyList
import com.iamnaran.firefly.ui.main.home.components.ProductPaginatedLazyList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val paginatedProducts = viewModel.paginatedProducts.collectAsLazyPagingItems()

    val homeState by viewModel.homeState.collectAsState()
    val loginStatus by viewModel.loginStatus.collectAsState()

    HomeContentPaginated(paginatedProducts) {
        onProductClick(it)
    }

}

@Composable
fun HomeContent(homeState: HomeState, onProductClick: (String) -> Unit) {

    ProductLazyList(homeState.allProductEntities) {
        onProductClick(it)
    }

}

@Composable
fun HomeContentPaginated(
    products: LazyPagingItems<ProductEntity>,
    onProductClick: (String) -> Unit
) {

    ProductPaginatedLazyList(products) {
        onProductClick(it)
    }

}

