package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography

@Composable
fun TravelCard() {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .height(150.dp)
                    .size(84.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Column(
                Modifier
                    .padding(10.dp),
            ) {
                Text(
                    text = "yourText".uppercase(),
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Your Title",
                    style = appTypography.titleLarge,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Your Description",
                    style = appTypography.bodySmall,
                    maxLines = 3,
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
fun TravelCardPreview() {
    FireflyComposeTheme {
        TravelCard()
    }
}
