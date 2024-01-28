package com.iamnaran.firefly.ui.main.home.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.home.components.ProductLazyList
import com.iamnaran.firefly.utils.AppLog

@Composable
fun ProductScreen(
    viewModel: ProductViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {

    val productState by viewModel.productState.collectAsState()

    val loginStatus by viewModel.loginStatus.collectAsState()

    AppLog.showLog("Home Screen Setup")

    ProductContent(productState){

    }


}

@Composable
fun ProductContent(productState: ProductState, onBackPressed: () -> Unit) {
    AppLog.showLog("Home Screen Content")

    Column {

        Text(text = "Details")

    }

}

