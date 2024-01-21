package com.iamnaran.firefly.ui.test

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FancyButton(label: String) {
    Button(onClick = { /* Handle button click */ }) {
        Text(text = label)
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    FancyButton("My first compose Button! Wohoo")
}
