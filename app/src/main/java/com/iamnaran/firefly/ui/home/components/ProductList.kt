package com.iamnaran.firefly.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme

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


@Composable
fun ProductLazyList(names: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(8.dp)){
        items(items =names){name ->
            ProductItem(name = name)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    FireflyComposeTheme {
        ProductLazyList()
    }
}

