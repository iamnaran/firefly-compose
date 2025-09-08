package com.iamnaran.firefly.ui.main.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.ui.main.home.components.ProductLazyList
import com.iamnaran.firefly.ui.main.home.components.ProductPaginatedLazyList

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val paginatedProducts = viewModel.paginatedProducts.collectAsLazyPagingItems()

    HomeContentPaginated(
        paginatedProducts
    ) {
        onProductClick(it)
    }

}

@Composable
fun HomeContent(homeState: HomeState, onProductClick: (String) -> Unit) {
    ProductLazyList(homeState.allProductEntities) {
        onProductClick(it)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeContentPaginated(
    products: LazyPagingItems<ProductEntity>,
    onProductClick: (String) -> Unit
) {
    ProductPaginatedLazyList(products) {
        onProductClick(it)
    }
}

