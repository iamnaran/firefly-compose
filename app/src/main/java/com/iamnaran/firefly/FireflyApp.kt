package com.iamnaran.firefly

import androidx.multidex.MultiDexApplication
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FireflyApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AppLog.init()
    }

}