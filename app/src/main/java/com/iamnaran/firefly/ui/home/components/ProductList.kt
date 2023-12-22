package com.iamnaran.firefly.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@Composable
fun ProductLazyList(names: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(8.dp)){
        items(items =names){name ->
            ProductItem(name = name)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    FireflyComposeTheme {
        ProductLazyList()
    }
}

