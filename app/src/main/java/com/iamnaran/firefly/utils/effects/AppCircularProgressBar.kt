package com.iamnaran.firefly.utils.effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppCircularProgressBar(
    progressType: ProgressType
) {
    val strokeWidth: Dp
    val progressBarSize: Dp

    when(progressType){
        ProgressType.REGULAR ->{
            strokeWidth = 8.dp
            progressBarSize = 30.dp
        }
        ProgressType.LARGE ->{
            strokeWidth = 8.dp
            progressBarSize = 40.dp
        }
        else -> {
            strokeWidth = 2.dp
            progressBarSize = 15.dp
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(progressBarSize),
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}

enum class ProgressType {
    SMALL, REGULAR, LARGE
}