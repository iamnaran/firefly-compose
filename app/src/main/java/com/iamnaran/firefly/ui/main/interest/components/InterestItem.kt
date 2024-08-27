package com.iamnaran.firefly.ui.main.interest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.data.dto.InterestModel
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.appTypography
import com.iamnaran.firefly.ui.theme.dimens
import kotlin.random.Random

@Composable
fun InterestItem(interestModel: InterestModel, onInterestItemClick: (InterestModel) -> Unit) {

    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.regular)
            .fillMaxWidth()
           ,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onInterestItemClick(interestModel)
                },
        ) {

            Text(
                text = interestModel.name,
                style = appTypography.bodyMedium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )

            Checkbox(checked = interestModel.isChecked, onCheckedChange = {
                onInterestItemClick(interestModel)
            })
        }
    }


}

@Preview(showBackground = true)
@Composable
fun InterestItemPreview() {
    FireflyComposeTheme {
        InterestItem(interestModel = InterestModel(Random.nextInt(), "Hello", true)) {

        }
    }
}