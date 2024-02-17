package com.iamnaran.firefly.ui.main.notification.ble.core

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.iamnaran.firefly.utils.AppLog


class BleManager(
    private val context: Context,
    private val onBleConnectionCallbacks: OnBleConnectionCallbacks

) : ScanCallback() {

    private var bluetoothManager: BluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    private var bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

    // states
    private var bleConnectionState: BleConnectionState = BleConnectionState.INITIAL

    private val handler = Handler(Looper.myLooper()!!)
    private var scanning = false
    // Stops scanning after 10 seconds.
    private val SCAN_PERIOD: Long = 10000

    public fun startScanning() {
        if (!bluetoothAdapter.isEnabled) {
            bleConnectionState = BleConnectionState.DISABLED
            onBleConnectionCallbacks.bluetoothStatus(bleConnectionState)

        } else {
            bleConnectionState = BleConnectionState.SCANNING
            onBleConnectionCallbacks.bluetoothStatus(bleConnectionState)
            scanBleDevices()

        }

    }

    // need permission check
    @SuppressLint("MissingPermission")
    private fun scanBleDevices() {
        if (!scanning) { // Stops scanning after a pre-defined scan period.
            handler.postDelayed({
                scanning = false
                bluetoothLeScanner.stopScan(this)
            }, SCAN_PERIOD)
            scanning = true
            bluetoothLeScanner.startScan(this)
        } else {
            scanning = false
            bluetoothLeScanner.stopScan(this)
        }

    }

    // need permission check
    @SuppressLint("MissingPermission")
    public fun stopBleScan() {
        try {
            bleConnectionState = BleConnectionState.STOPPED
            bluetoothLeScanner.stopScan(this)
        } catch (_: Exception) {

        }

    }

    override fun onScanResult(callbackType: Int, result: ScanResult) {
        super.onScanResult(callbackType, result)
        AppLog.showLog("Ble Result Called")

        onBleConnectionCallbacks.onDeviceFound(result.device)
    }



    override fun onBatchScanResults(results: MutableList<ScanResult>?) {
        super.onBatchScanResults(results)
        AppLog.showLog("Ble Result Called ${results?.size}")
        val scanResult = results?.get(0)
        onBleConnectionCallbacks.onDeviceFound(scanResult!!.device)

    }

    override fun onScanFailed(errorCode: Int) {
        super.onScanFailed(errorCode)
        AppLog.showLog("Ble Failed $errorCode")
    }


}


interface OnBleConnectionCallbacks {

    fun onConnectionSuccess()

    fun onDeviceFound(bluetoothDevice: BluetoothDevice)

    fun bluetoothStatus(bleConnectionState: BleConnectionState)

}

enum class BleConnectionState {
    INITIAL,
    SCANNING,
    DISABLED,
    STOPPED,
}

fun Context.hasBluetoothPermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH_SCAN
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH_SCAN
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        TODO("VERSION.SDK_INT < S")
    }
}