package com.example.submission2belajarfundamentalaplikasiandroid.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfiguration {

    private const val MY_GITHUB_API_KEY = "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"//replace the github API
    private const val GITHUB_URL = "https://api.github.com"

    private val client by lazy{
        OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", MY_GITHUB_API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

    }

    private val retrofitBuilder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(GITHUB_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val CLIENT_API : ClientAPI by lazy{
        retrofitBuilder.build().create(ClientAPI::class.java)
    }
}
