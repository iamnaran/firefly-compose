package com.iamnaran.firefly.ui.main.home.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.firefly.data.local.entities.Product
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@Composable
fun ProductLazyList(allProducts: List<Product>) {
    LazyColumn(){
        items(items =allProducts){product ->
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

