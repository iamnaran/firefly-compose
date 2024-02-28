package com.iamnaran.firefly.ui.main.notification.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.iamnaran.firefly.ui.main.notification.NotificationState
import com.iamnaran.firefly.ui.main.notification.core.SensorMetaData
import com.iamnaran.firefly.ui.main.notification.core.SensorMetaManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun RecipeScreen(notificationState: NotificationState, onRecipeClick: (String) -> Unit) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var data by remember { mutableStateOf<SensorMetaData?>(null) }

    DisposableEffect(Unit) {
        val sensorMetaManager = SensorMetaManager(context)
        sensorMetaManager.init()

        val job = scope.launch {
            sensorMetaManager.data
                .receiveAsFlow()
                .onEach {
                    data = it
                }
                .collect()
        }

        onDispose {
            sensorMetaManager.unRegisterSensors()
            job.cancel()
        }
    }

    RecipeList(data,notificationState.allRecipeList,onRecipeClick)



}


