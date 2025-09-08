package com.iamnaran.firefly.ui.main.home.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductPaginatedLazyList(
    products: LazyPagingItems<ProductEntity>,
    onProductClick: (String) -> Unit
) {
    LazyColumn {
        items(products.itemCount) { index ->
            val product = products[index]
            product?.let {
                ProductItem(product) {
                    onProductClick(product.id.toString())
                }
            }
        }

        // Optional: Handle loading or error states
        products.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        // Show a loading spinner or placeholder
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        // Show a loading indicator at the bottom
                        CircularProgressIndicator(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = products.loadState.refresh as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.localizedMessage}", color = Color.Red)
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = products.loadState.append as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.localizedMessage}", color = Color.Red)
                    }
                }
            }
        }
    }
}


