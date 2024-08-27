package com.iamnaran.firefly.ui.main.interest.components.account_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.dto.AccountModel
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens
import kotlin.random.Random

@Composable
fun AccountItemHeader(accountModel: AccountModel, onAccountClick: (AccountModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.regular)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onAccountClick(accountModel)
                },
        ) {

            ProfileImageView("https://fastly.picsum.photos/id/183/200/300.jpg?hmac=Z9yCtuuIPn5CuOhwIntNEQFIRotghuBn06nqOSL828c")

            Column {
                Text(
                    text = accountModel.name,
                    style = appTypography.bodyMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = accountModel.username,
                    style = appTypography.bodySmall,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}


@Composable
fun AccountItemFooter(onFooterClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.regular)
            .fillMaxWidth()
            .background(Color.LightGray),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onFooterClicked()
                },
        ) {
            Text(
                text = "Add New Account",
                style = appTypography.bodyMedium,
                maxLines = 1,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Composable
fun ProfileImageView(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
    )
}


@Preview(showBackground = true)
@Composable
fun AccountItemHeaderPreview() {
    FireflyComposeTheme {
        AccountItemHeader(
            accountModel = AccountModel(
                Random.nextInt(),
                "The Crafter Penguins",
                "@crafterpenguins",
                "https://fastly.picsum.photos/id/183/200/300.jpg?hmac=Z9yCtuuIPn5CuOhwIntNEQFIRotghuBn06nqOSL828c",
                true
            )
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountItemFooterPreview() {
    FireflyComposeTheme {
        AccountItemFooter() {
        }
    }
}