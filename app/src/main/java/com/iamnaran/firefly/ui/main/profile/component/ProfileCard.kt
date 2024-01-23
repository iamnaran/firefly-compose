package com.iamnaran.firefly.ui.main.profile.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens

@Composable
fun ProfileCard(userEntity: UserEntity) {

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.large)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box {
                AsyncImage(
                    model = userEntity.profileImage,
                    contentDescription = userEntity.fullName,
                    modifier = Modifier
                        .padding(MaterialTheme.dimens.extraLarge)
                        .width(150.dp)
                        .height(150.dp)
                        .clip(CircleShape)
                        .border(
                            MaterialTheme.dimens.extraSmall,
                            MaterialTheme.colorScheme.primary,
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop,
                )
            }


            Column(
                Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = userEntity.fullName,
                    style = appTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(MaterialTheme.dimens.medium)
                )

                Text(
                    text = userEntity.email.lowercase(),
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(MaterialTheme.dimens.medium)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium))


            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {
        ProfileCard(
            UserEntity(
                12,
                "Full Name",
                "nrn.panthi@gmail.com",
                "121211212",
                "Hello World"
            )
        )
    }
}