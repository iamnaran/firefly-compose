package com.iamnaran.firefly.ui.main.explore.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iamnaran.firefly.domain.dto.CategoryWithProducts


@Composable
fun ExploreTest(data: List<String>) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(5),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data.size) { index ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = data[index],
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }

}

@Composable
fun LazyRowItem(data: List<String>) {
    LazyRow {
        items(data) { item ->
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(200.dp)
                    .background(Color.Magenta)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }

}

@Composable
fun NestedColumn(data: List<String>) {

    LazyColumn() {


        items(data) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Magenta)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))
        }


    }
}

@Composable
fun NestedLazyColumn(data: List<String>) {

    LazyColumn() {

        item {
            HeaderItem()
        }
        items(data) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Magenta)
                    .padding(16.dp)
            )
            
            Spacer(modifier = Modifier.padding(8.dp))
        }
        item {
            FooterItem()
        }

    }
}


@Composable
fun HeaderItem() {
    Column {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(16.dp)
        ) {
            Text("Header Item", maxLines = 5)
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}


@Composable
fun FooterItem() {
    Column {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(16.dp)
        ) {
            Text(text = "Footer Item", maxLines = 5)
        }
        Spacer(modifier = Modifier.padding(8.dp))
    }
}


//    LazyColumn(
//        state = listState
//    ) {
//        items(allProductEntities) { allProducts ->
//            ExploreHeader(allProducts.categoryName)
//            LazyRow {
//                items(allProducts.products, key = { product -> product.id }) { product ->
//                    ExploreItem(productEntity = product) {
//
//                    }
//                }
//            }
//        }
//    }

//    LazyColumn(
//        state = listState
//    ) {
//        items(allProductEntities) { allProducts ->
//            ExploreHeader(allProducts.categoryName)
//
//            allProducts.products.forEach{ product ->
//                Column {
//                    ExploreItem(productEntity = product) {
//
//                    }
//                }
//            }
//        }
//    }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreContentTest(allProductEntities: List<CategoryWithProducts>) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {

        allProductEntities.map { (categoryName, productList) ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onPrimary)
                ) {
                    Text(
                        text = categoryName.uppercase(),
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                    )
                }
            }
            item(productList) {
                LazyRow() {
                    items(
                        items = productList,
                        key = { productData -> productData.id }) { productData ->
                        ExploreItem(productEntity = productData) {

                        }

                    }
                }
            }


        }


    }
}


