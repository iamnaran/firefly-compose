package com.iamnaran.firefly.ui.main.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@Composable
fun ProductLazyList(allProductEntities: List<ProductEntity>) {
    LazyColumn(){
        items(items =allProductEntities){ product ->
            ProductItem(product)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    FireflyComposeTheme {
        ProductLazyList(emptyList())
    }
}

