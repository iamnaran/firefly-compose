package com.iamnaran.firefly.ui.main.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.explore.components.ExploreList

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val exploreState by viewModel.exploreState.collectAsState()

    ExploreContent(exploreState) {
        onProductClick(it)
    }

}

@Composable
fun ExploreContent(exploreState: ExploreState, onProductClick: (String) -> Unit) {
    Column {
        ExploreList(exploreState.allProductEntities) {
            onProductClick(it)
        }
    }

}

