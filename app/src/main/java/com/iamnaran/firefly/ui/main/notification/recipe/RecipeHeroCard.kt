package com.iamnaran.firefly.ui.main.notification.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.main.notification.core.SensorMetaData

@SuppressLint("UnrememberedMutableState")
@Composable
fun RecipeHeroCard(
    biasConstant: Int = 10,
    dpValue: Float = 0.9f,
    data: SensorMetaData?
) {
    val xPitch by
    derivedStateOf { (data?.xPitch ?: 0f) * biasConstant }

    val yRoll by
    derivedStateOf { (data?.yRoll ?: 0f) * biasConstant }

    val zAzimuth by
    derivedStateOf { (data?.zAzimuth ?: 0f) * biasConstant }


    Column(
        Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(modifier = Modifier
            .offset {
                IntOffset(
                    x = (yRoll * 0.4).dp.roundToPx(),
                    y = -(xPitch * 0.6).dp.roundToPx()
                )
            }
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
        ) {
            Box {
                AsyncImage(
                    model = "https://img.hotimg.com/MV5BMDg2YzI0ODctYjliMy00NTU0LTkxODYtYTNkNjQwMzVmOTcxXkEyXkFqcGdeQXVyNjg2NjQwMDQ._V1_.jpeg",
                    contentDescription = "Hero Card",
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        Color.Black.copy(alpha = 0.3f))

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_image_big_glow),
                    contentDescription = "Glow",
                    modifier = Modifier
                        .size(width = 400.dp, height = 400.dp)
                        .padding(top = 20.dp)
                        .offset {
                            IntOffset(
                                x = -(yRoll * 10).dp.roundToPx(),
                                y = (xPitch * 4).dp.roundToPx()
                            )
                        })
            }

        }


    }


}
