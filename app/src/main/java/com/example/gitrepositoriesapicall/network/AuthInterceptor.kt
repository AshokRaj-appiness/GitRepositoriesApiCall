package com.example.gitrepositoriesapicall.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request.newBuilder()?.addHeader("Authorization","key")
        return chain.proceed(request)
    }
}