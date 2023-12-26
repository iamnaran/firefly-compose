package com.iamnaran.firefly.data.api

import com.iamnaran.firefly.data.preference.PreferenceHelper
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.internal.ignoreIoExceptions
import javax.inject.Inject

class SupportAuthenticator @Inject constructor(
    private val preferencesHelper: PreferenceHelper
) :
    Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        var requestAvailable: Request? = null

        try {

            requestAvailable = response.request.newBuilder()
                .addHeader(
                    "Authorization", preferencesHelper.getAccessToken() ?: ""
                )
                .addHeader("Accept", "Accept: application/json")
                .build()
            return requestAvailable
        } catch (ex: Exception) {
            ignoreIoExceptions { }
        }
        return requestAvailable

    }
}