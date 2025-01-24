package com.iamnaran.firefly.ui.main.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    sharedTransitionScope: SharedTransitionScope,
    onProductClick: (String) -> Unit,
) {
    val paginatedProducts = viewModel.paginatedProducts.collectAsLazyPagingItems()

    HomeContentPaginated(
        paginatedProducts,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
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
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onProductClick: (String) -> Unit
) {
    ProductPaginatedLazyList(products, sharedTransitionScope,animatedContentScope) {
        onProductClick(it)
    }
}

