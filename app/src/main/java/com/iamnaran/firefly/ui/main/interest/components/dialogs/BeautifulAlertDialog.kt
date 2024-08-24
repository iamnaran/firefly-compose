package com.iamnaran.firefly.ui.main.interest.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme

@Composable
fun BeautifulAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painter,
                        contentDescription = imageDescription,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(180.dp)
                    )
                }
                Text(
                    text = "New Update Available",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                )

                Text(
                    text = "This is an example of the description of a very beautiful dialog which you may not like.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 8.dp),
                )
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("Update Tonight")
                }
                TextButton(
                    onClick = { onConfirmation() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(style = MaterialTheme.typography.bodySmall, text = "View Released Notes")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {

        BeautifulAlertDialog(
            onDismissRequest = { },
            onConfirmation = {
            },
            painter = painterResource(id = R.drawable.ic_app_logo),
            imageDescription = "Sample Image"
        )

    }
}