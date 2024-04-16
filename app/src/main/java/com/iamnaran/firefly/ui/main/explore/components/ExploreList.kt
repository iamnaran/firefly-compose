package com.iamnaran.firefly.ui.main.explore.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.domain.dto.CategoryWithProducts
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreList(allProductEntities: List<CategoryWithProducts>, onProductClick: (String) -> Unit) {

//    ExploreContent(allProductEntities)
    ExploreContentSimple(allProductEntities)


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreContent(allProductEntities: List<CategoryWithProducts>) {

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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreContentSimple(allProductEntities: List<CategoryWithProducts>) {

    val listState = rememberLazyListState()

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

    LazyColumn(
        state = listState,
    ) {
        if (allProductEntities.isNotEmpty()) {
            allProductEntities.map { (categoryName, productList) ->
                stickyHeader {
                    ExploreHeader(categoryName)
                }
                EventItemNew(productList) {

                }
            }

        } else {
            item {
                EmptyStateView()
            }
        }

    }


}

@Composable
fun EmptyStateView() {

    Text(text = "This is empty state message we got")

}

fun LazyListScope.EventItem(productList: List<ProductEntity>) {
    items(productList) { product ->
        Column {
            Text(text = product.title, modifier = Modifier.padding(10.dp))
        }
    }

}

fun LazyListScope.EventItemNew(
    productEntities: List<ProductEntity>,
    onProductItemClick: (String) -> Unit
) {

    items(productEntities) { productEntity ->

        Card(
            modifier = Modifier
                .padding(MaterialTheme.dimens.regular)
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    onProductItemClick(productEntity.id.toString())
                },
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
            ) {
                AsyncImage(
                    model = productEntity.thumbnail,
                    contentDescription = productEntity.title,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    Modifier
                        .padding(10.dp),
                ) {

                    Text(
                        text = productEntity.title,
                        style = appTypography.bodyMedium,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = productEntity.description,
                        style = appTypography.bodySmall,
                        minLines = 1,
                        maxLines = 2,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }

}

@Composable
fun ExploreHeader(title: String) {
    Text(text = title, modifier = Modifier.padding(9.dp))
}

