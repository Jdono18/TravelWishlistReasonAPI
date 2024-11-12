package com.example.travelwishlistreasonapi

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithHeaders = chain.request().newBuilder()
            .addHeader("Authorization", "")
            .build()

        return chain.proceed(requestWithHeaders)
    }
}