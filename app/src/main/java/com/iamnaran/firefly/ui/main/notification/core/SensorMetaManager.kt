package com.iamnaran.firefly.ui.main.notification.core

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.Channel

public class SensorMetaManager(context: Context) : SensorEventListener {

    private val sensorManager: SensorManager? by lazy {
        context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    }

    private val NS2S = 1.0f / 1000000000.0f

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var gravity: FloatArray? = null
    private var geomagnetic: FloatArray? = null

    val data: Channel<SensorMetaData> = Channel(Channel.UNLIMITED)


    fun init() {
        val magnetSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val gyroSensor: Sensor? = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        val accelerometerSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_GRAVITY)
        sensorManager?.registerListener(
            this,
            gyroSensor,
            SensorManager.SENSOR_DELAY_NORMAL,
            SensorManager.SENSOR_DELAY_UI
        )
        sensorManager?.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI)
        sensorManager?.registerListener(this, magnetSensor, SensorManager.SENSOR_DELAY_UI)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GRAVITY)
            gravity = event.values

        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD)
            geomagnetic = event.values


        if (gravity != null && geomagnetic != null) {
            val r = FloatArray(9)
            val i = FloatArray(9)

            if (SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(r, orientation)

                data.trySend(
                    SensorMetaData(
                        zAzimuth = orientation[0],
                        yRoll = orientation[2],
                        xPitch = orientation[1]
                    )

                )
            }
        }


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {


    }

    fun unRegisterSensors() {
        // unregister all sensors
        sensorManager?.unregisterListener(this)
    }



}

data class SensorMetaData(
    val xPitch: Float,
    val yRoll: Float,
    val zAzimuth: Float

)