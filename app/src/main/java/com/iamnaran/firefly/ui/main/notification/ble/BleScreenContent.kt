package com.iamnaran.firefly.ui.main.notification.ble

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.ui.main.notification.ble.component.DeviceInfoList
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware

@Composable
fun BleScreenContent(bleViewModel: BleViewModel = hiltViewModel(), onScanClick: () -> Unit) {

    val bleDevicesState = bleViewModel.bleDeviceState.collectAsStateLifecycleAware()

    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { onScanClick() }) {
            Text(text = "Scan Devices")
        }

        DeviceInfoList(allDevices = bleDevicesState.value.allBleDeviceEntity) {

        }


    }
}