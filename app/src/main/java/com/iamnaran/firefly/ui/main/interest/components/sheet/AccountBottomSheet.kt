package com.iamnaran.firefly.ui.main.interest.components.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetDialog(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onActionConfirmation: () -> Unit,
) {


    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // Sheet content
            Button(onClick = {
                onDismissRequest()
            }) {
                Text("Hide bottom sheet")
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ai_png),
                    contentDescription = "imageDescription",
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
                onClick = { onActionConfirmation() },
                modifier = Modifier.padding(8.dp),
            ) {
                Text(style = MaterialTheme.typography.bodySmall, text = "Okay")
            }
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }


}