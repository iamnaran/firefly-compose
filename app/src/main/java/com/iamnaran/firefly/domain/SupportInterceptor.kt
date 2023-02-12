package com.iamnaran.firefly.domain

import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Cache-Control", "max-age=60")
            .build()
        return chain.proceed(request)
    }

}