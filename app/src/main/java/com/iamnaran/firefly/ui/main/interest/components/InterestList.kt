package com.iamnaran.firefly.ui.main.interest.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.iamnaran.firefly.data.dto.InterestModel

@Composable
fun InterestList(interests: List<InterestModel>, onInterestSelected: (InterestModel) -> Unit) {

    LazyColumn {
        items(interests.size) { index ->
            InterestItem(interests[index], onInterestSelected)
        }
    }


}


