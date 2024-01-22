package com.iamnaran.firefly.ui.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.local.entities.Product
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appLightSecondary
import com.iamnaran.firefly.ui.theme.appTextPrimary
import com.iamnaran.firefly.ui.theme.appTextSecondary
import com.iamnaran.firefly.ui.theme.appTypography

@Composable
fun ProductItem(product: Product) {

    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(15.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = appLightSecondary,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .background(appLightSecondary)
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop,
            )

            Column(
                Modifier
                    .padding(10.dp),
            ) {
                Text(
                    text = product.category.uppercase(),
                    style = appTypography.labelMedium,
                    color = appTextSecondary,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = product.title,
                    style = appTypography.titleLarge,
                    maxLines = 2,
                    color = appTextPrimary,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = product.description,
                    style = appTypography.bodySmall,
                    maxLines = 3,
                    color = appTextSecondary,
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
            Product(
                12,
                "Iphone 15 Pro Design Methods of the society is great of Iphone 15 Pro Design",
                "It seems that you are trying to fill up the rounded edges of a CardView in Jetpack Compose with an image or ripple animation.",
                "sas",
                "Category",
                12,
                "asa",
                12,
                "asas"
            )
        )
    }
}