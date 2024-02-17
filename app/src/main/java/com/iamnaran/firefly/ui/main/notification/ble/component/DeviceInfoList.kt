package com.iamnaran.firefly.ui.main.notification.ble.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iamnaran.firefly.ui.main.notification.ble.dto.BleDeviceEntity
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme


@Composable
fun DeviceInfoList(allDevices: List<BleDeviceEntity>, onBleDeviceClick: (BleDeviceEntity) -> Unit) {
    LazyColumn {
        items(items = allDevices, key = { item -> item.deviceName }) { deviceInfo ->
            DeviceInfoItem(deviceInfo) {
                onBleDeviceClick(deviceInfo)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultHomePreview() {
    FireflyComposeTheme {
        DeviceInfoList(emptyList()) {

        }
    }
}

