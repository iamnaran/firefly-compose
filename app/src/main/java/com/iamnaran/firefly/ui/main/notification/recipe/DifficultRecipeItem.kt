package com.iamnaran.firefly.ui.main.notification.recipe

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens

@Composable
fun DifficultRecipeItem(recipeEntity: RecipeEntity, onRecipeClick: (String) -> Unit) {

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.medium)
            .shadow(
                elevation = 5.dp,
                spotColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onRecipeClick(recipeEntity.id.toString())
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = recipeEntity.image,
                contentDescription = recipeEntity.name,
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
                    text = recipeEntity.difficulty,
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = recipeEntity.name,
                    style = appTypography.titleLarge,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }


}
