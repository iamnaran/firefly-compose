package com.iamnaran.firefly.ui.main.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.R


@Composable
fun ExploreTest(data: List<String>) {

//    LazyRow {
//        items(data) { item ->
//            Box(
//                modifier = Modifier
//                    .width(100.dp)
//                    .height(200.dp)
//                    .background(Color.Magenta)
//                    .padding(16.dp)
//            )
//            Spacer(modifier = Modifier.padding(8.dp))
//        }
//    }

    LazyColumn {
        items(data) { item ->
            Column {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Magenta)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = stringResource(id = R.string.lorem_ipsum), maxLines = 5)
            }

        }
    }
}

