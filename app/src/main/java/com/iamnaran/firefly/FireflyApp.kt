package com.iamnaran.firefly

import android.app.Application
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FireflyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppLog.init()
    }

}