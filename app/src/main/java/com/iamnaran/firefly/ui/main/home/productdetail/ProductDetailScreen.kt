package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.iamnaran.firefly.ui.navigation.topappbar.ChildAppTopBar
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.utils.extension.getFirstTwoWords

@Composable
fun ProductDetailScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    productId: String?,
    onBackPressed: () -> Unit,
) {
    val productState by viewModel.productState.collectAsState()

    LaunchedEffect(Unit){
        if (productId != null){
            viewModel.getProductById(productId)
        }
    }

    if (productState.productEntity != null) {
        ProductContent(productState.productEntity!!) {
            onBackPressed()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductContent(
    productEntity: ProductEntity, onBackPressed: () -> Unit
) {
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            ChildAppTopBar(
                toolbarTitle = productEntity.title.getFirstTwoWords(),
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
            Column {
                AsyncImage(
                    model = productEntity.thumbnail,
                    contentDescription = productEntity.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                )

                Column(
                    Modifier
                        .padding(10.dp),
                ) {
                    Text(
                        text = productEntity.category.uppercase(),
                        style = appTypography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = productEntity.title,
                        style = appTypography.titleLarge,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = productEntity.description,
                        style = appTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(8.dp)
                    )

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
        ProductContent(
            ProductEntity(
                12,
                "Iphone 15 Pro Design Methods of the society is great of Iphone 15 Pro Design",
                "It seems that you are trying to fill up the rounded edges of a CardView in Jetpack Compose with an image or ripple animation.",
                "Category",
                12f,
                "asa",
                12,
                "asas"
            )
        ) {

        }
    }
}


