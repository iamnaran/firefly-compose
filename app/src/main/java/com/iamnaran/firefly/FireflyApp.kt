package com.iamnaran.firefly

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.iamnaran.firefly.utils.AppLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FireflyApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        AppLog.init()
    }

}