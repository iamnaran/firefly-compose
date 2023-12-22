package com.iamnaran.firefly.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.R

@Composable
fun ProductItem(name: String) {
    Row(
        Modifier
            .background(Color.Cyan)
            .padding(30.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_google_logo),
            contentDescription = "logo",
            Modifier
                .size(150.dp)
                .padding(8.dp)
        )

        Button(onClick = { }, Modifier.fillMaxWidth()) {
            Text(text = name, Modifier.padding(vertical = 8.dp))
        }
    }
}