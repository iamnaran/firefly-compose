package com.iamnaran.firefly.ui.main.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.ui.main.notification.ble.BleScreenContent

@Composable
fun NotificationScreen(onScanClick: () -> Unit) {
    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BleScreenContent() {
            onScanClick()
        }

        Spacer(modifier = Modifier.padding(20.dp))

    }

}

@Composable
fun EditProfileContent(fullName: String, onScanClick: () -> Unit) {


}

