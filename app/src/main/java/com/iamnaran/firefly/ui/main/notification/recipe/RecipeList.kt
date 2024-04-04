package com.iamnaran.firefly.ui.main.notification.recipe

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.ui.main.notification.core.SensorMetaData
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@Composable
fun RecipeList(
    sensorMetaData: SensorMetaData?,
    dataList: List<RecipeEntity>,
    onRecipeClick: (String) -> Unit
) {
    LazyColumn {
        itemsIndexed(items = dataList) { index, data ->

            if (index == 0) {
                RecipeHeroCard(data = sensorMetaData)

            } else {
                when (data.difficulty) {

                    "Difficult" -> {
                        DifficultRecipeItem(data) {
                            onRecipeClick(data.id.toString())
                        }
                    }

                    else -> {
                        EasyRecipeItem(data) {
                            onRecipeClick(data.id.toString())
                        }
                    }

                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    FireflyComposeTheme {
        RecipeList(SensorMetaData(1.2f, 1.2f, 1.2f), emptyList()) {

        }
    }
}

