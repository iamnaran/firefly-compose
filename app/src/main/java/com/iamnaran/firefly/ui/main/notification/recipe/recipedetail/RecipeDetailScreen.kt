package com.iamnaran.firefly.ui.main.notification.recipe.recipedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.ui.main.home.components.ProductItem
import com.iamnaran.firefly.ui.navigation.topappbar.ChildAppTopBar
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.utils.extension.getFirstTwoWords

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeViewModel = hiltViewModel(),
    recipeId: String?,
    onBackPressed: () -> Unit,
) {
    val recipeState by viewModel.productState.collectAsState()

    LaunchedEffect(Unit) {
        if (recipeId != null) {
            viewModel.getRecipeById(recipeId)
        }
    }

    if (recipeState.recipeEntity != null) {
        RecipeContent(recipeState.recipeEntity!!) {
            onBackPressed()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContent(
    recipeEntity: RecipeEntity, onBackPressed: () -> Unit
) {
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            ChildAppTopBar(
                toolbarTitle = recipeEntity.name.getFirstTwoWords(),
                barScrollBehavior = barScrollBehavior,
                onBackPressed
            )
        }, modifier = Modifier
            .fillMaxSize()
            .nestedScroll(barScrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn() {
                item {
                    AsyncImage(
                        model = recipeEntity.image,
                        contentDescription = recipeEntity.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                    )
                }
                item {
                    Text(
                        text = recipeEntity.difficulty.uppercase(),
                        style = appTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                item {
                    Text(
                        text = recipeEntity.name,
                        style = appTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                item {

                    Text(
                        text = recipeEntity.instructions.joinToString(),
                        style = appTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))

                }


            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {

    }
}


