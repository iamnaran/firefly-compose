package com.iamnaran.firefly.ui.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens

@Composable
fun ProductItem(productEntity: ProductEntity, onProductItemClick: (String) -> Unit) {

    val context = LocalContext.current

    val imageRequest = remember(productEntity.thumbnail) {
        ImageRequest.Builder(context)
            .data(productEntity.thumbnail)
            .size(200, 200)
            .scale(Scale.FILL)
            .build()
    }

    val isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.regular)
            .shadow(
                elevation = 5.dp,
                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
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
                model = imageRequest,
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
                    text = productEntity.category.uppercase(),
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = productEntity.title,
                    style = appTypography.titleLarge,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = productEntity.description,
                    style = appTypography.bodySmall,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {
        ProductItem(
            ProductEntity(
                12,
                "Iphone 15 Pro Design Methods of the society is great of Iphone 15 Pro Design",
                "It seems that you are trying to fill up the rounded edges of a CardView in Jetpack Compose with an image or ripple animation.",
                "sas",
                12f,
                "12",
                13,
                "asas",
            )
        ) {

        }
    }
}