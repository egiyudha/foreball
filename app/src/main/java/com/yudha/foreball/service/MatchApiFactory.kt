package com.yudha.foreball.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MatchApiFactory {

    companion object {
        fun initAPI(): MatchApi {
            return Retrofit.Builder()
                .baseUrl("https://stroccoli-futbol-science-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initHTTPClient())
                .build()
                .create(MatchApi::class.java)
        }

        private fun initHTTPClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(Authorize())
                .build()
        }
    }

    private class Authorize : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val initURL = request.newBuilder()
                .addHeader("x-rapidapi-host", "stroccoli-futbol-science-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "57c27ac375msh5ae40ec32ea48e4p10def2jsn4800c4ef40fa")
                .build()
            return chain.proceed(initURL)
        }
    }
}