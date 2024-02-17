package com.iamnaran.firefly.ui.main.notification.ble

import androidx.lifecycle.ViewModel
import com.iamnaran.firefly.domain.usecase.product.GetProductUseCase
import com.iamnaran.firefly.ui.appcomponent.BaseViewModel
import com.iamnaran.firefly.ui.main.notification.ble.BleDevicesState
import com.iamnaran.firefly.ui.main.notification.ble.dto.BleDeviceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BleViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
): BaseViewModel() {

    private val _bleDeviceState = MutableStateFlow(BleDevicesState())
    val bleDeviceState = _bleDeviceState.asStateFlow()


    fun updateBleDevicesList(deviceName: String){
        val bleDeviceEntity = BleDeviceEntity(deviceName)
        val bleDevicesList = arrayListOf(bleDeviceEntity)
        _bleDeviceState.value = BleDevicesState(bleDevicesList)

    }

}