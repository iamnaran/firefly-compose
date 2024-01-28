package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.home.components.ProductLazyList
import com.iamnaran.firefly.utils.AppLog

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val productState by viewModel.productState.collectAsState()

    AppLog.showLog("Home Screen Setup")

    if (productState.productEntity != null){
        AppLog.showLog("----Called----"+ productState.productEntity!!.brand)
        ProductContent(productState) {

        }
    }




}

@Composable
fun ProductContent(productState: ProductState, onBackPressed: () -> Unit) {
    AppLog.showLog("Home Screen Content")

    Column(modifier = Modifier
        .background(Color.Cyan)
        .fillMaxWidth()
        .fillMaxHeight()) {
        Text(text = "Details")

    }

}

