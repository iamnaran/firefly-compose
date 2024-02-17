package com.iamnaran.firefly.ui.main.notification.ble

import com.iamnaran.firefly.ui.main.notification.ble.dto.BleDeviceEntity

data class BleDevicesState(
    val allBleDeviceEntity: List<BleDeviceEntity> = emptyList()
)