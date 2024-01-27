package com.iamnaran.firefly.utils

import timber.log.Timber

class AppLog private constructor() {



    companion object {

        fun init() {
            Timber.plant(Timber.DebugTree())
            // You can add additional trees or modify the initialization as needed
            // For example, you might want to add a custom tree for release builds
            // Timber.plant(CrashReportingTree())
        }

        fun showDebug(tag: String, message: String) {
            Timber.d("$tag--->$message")
        }

        fun showLog(message: String) {
            Timber.d("showlog show log--->$message")
        }

        fun showError(tag: String, message: String) {
            Timber.e("$tag--->$message")

        }

        fun showException(throwable: Throwable, message: String, vararg args: Any?) {
            Timber.e(throwable, message, *args)
        }

        fun tagFor(clazz: Class<*>): String {
            return clazz.simpleName
        }
    }
}