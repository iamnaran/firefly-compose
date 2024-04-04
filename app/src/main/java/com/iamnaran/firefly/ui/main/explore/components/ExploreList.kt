package com.iamnaran.firefly.ui.main.explore.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.domain.dto.CategoryWithProducts


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreList(allProductEntities: List<CategoryWithProducts>, onProductClick: (String) -> Unit) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {

        allProductEntities.map { (categoryName, productList) ->
            stickyHeader {
                // Render your sticky header here
                // For example:
                Text(text = categoryName)
            }
            item(productList){
                LazyRow (){
                    items(
                        items = productList,
                        key = { productData -> productData.id }) { productData ->
                        ExploreItem(productEntity = productData) {
                            onProductClick(productData.title)
                        }

                    }
                }
            }


        }


    }
}

