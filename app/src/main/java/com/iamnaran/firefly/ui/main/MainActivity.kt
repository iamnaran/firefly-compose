package com.iamnaran.firefly.ui.main

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.ui.auth.login.LoginScreen
import com.iamnaran.firefly.ui.main.notification.ble.core.BleConnectionState
import com.iamnaran.firefly.ui.main.notification.ble.core.BleManager
import com.iamnaran.firefly.ui.main.notification.ble.core.OnBleConnectionCallbacks
import com.iamnaran.firefly.ui.navigation.AppScreen
import com.iamnaran.firefly.ui.navigation.bottomappbar.BottomBar
import com.iamnaran.firefly.ui.navigation.multiplebackstack.RootMultipleBackStackNavHost
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() , OnBleConnectionCallbacks{

    private val TAG: String = AppLog.tagFor(this.javaClass)
    private lateinit var navController: NavHostController
    private val mainViewModel: MainViewModel by viewModels()

    private var isAuthenticated = false

    private lateinit var bleManager: BleManager

    override fun onStop() {
        super.onStop()
        bleManager.stopBleScan()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBleManager()
        isAuthenticated = mainViewModel.isUserAuthenticated()
        setContent {
            FireflyComposeTheme {
                navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val topBarState = rememberSaveable { (mutableStateOf(true)) }

                // Control TopBar and BottomBar
                when (navBackStackEntry?.destination?.route) {
                    AppScreen.Main.Home.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    AppScreen.Main.Profile.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    AppScreen.Main.Notification.route -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    else -> {
                        bottomBarState.value = false
                        topBarState.value = false
                    }
                }

                MainScreenContent(snackbarHostState,bottomBarState,navController,isAuthenticated)

            }
        }
    }

    private fun initBleManager() {

        bleManager = BleManager(this,this)
        bleManager.startScanning()

    }

    override fun onConnectionSuccess() {

        AppLog.showLog("Ble Called Success")

    }

    override fun onDeviceFound(bluetoothDevice: BluetoothDevice) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        AppLog.showLog("Ble Called Device ${bluetoothDevice.name}")

    }

    override fun bluetoothStatus(bleConnectionState: BleConnectionState) {

        AppLog.showLog("Ble Called status$bleConnectionState")

    }

}

@Composable
private fun MainScreenContent(
    snackbarHostState: SnackbarHostState,
    bottomBarState: MutableState<Boolean>,
    navController: NavHostController,
    isAuthenticated: Boolean
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            if (bottomBarState.value) {
                BottomBar(navController = navController)
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            /*
            todo For normal back stack nav host
            RootNavHost(
                isAuthenticated,
                navHostController = navController
            )*/

            // For multiple back stack nav host
            RootMultipleBackStackNavHost(
                isAuthenticated,
                rootNavHostController = navController
            )
        }
    }


}




@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FireflyComposeTheme {
        LoginScreen(
            navigateToHome = {},
            navigateToSignUp = {})
    }
}




//https://gist.github.com/stevdza-san/5743a32dc1ec2d8eaf62c29d9bab1c43