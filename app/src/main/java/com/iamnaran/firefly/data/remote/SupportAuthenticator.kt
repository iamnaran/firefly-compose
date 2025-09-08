package com.iamnaran.firefly.data.remote

import com.iamnaran.firefly.data.preference.PreferenceHelper
import com.iamnaran.firefly.utils.AppLog
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class SupportAuthenticator @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) :
    Authenticator {

    val tag = AppLog.tagFor(this.javaClass)

    override fun authenticate(route: Route?, response: Response): Request {

        val requestBuilder: Request.Builder = response.request.newBuilder()
        requestBuilder.addHeader("Accept", "Accept: application/json")
        try {
            requestBuilder.addHeader("Authorization", preferenceHelper.getAccessToken().toString())
            return requestBuilder.build()
        } catch (ex: Exception) {
            AppLog.showDebug(tag, ex.message.toString())
        }
        return requestBuilder.build()

    }
}