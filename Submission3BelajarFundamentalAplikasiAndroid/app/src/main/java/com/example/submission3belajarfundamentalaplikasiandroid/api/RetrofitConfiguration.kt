package com.example.submission3belajarfundamentalaplikasiandroid.api

import com.example.submission3belajarfundamentalaplikasiandroid.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfiguration {

    private const val MY_GITHUB_API_KEY = BuildConfig.KEY//replace the github API
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
            .addConverterFactory(MoshiConverterFactory.create())
    }

    val CLIENT_API : ClientAPI by lazy{
        retrofitBuilder.build().create(ClientAPI::class.java)
    }
}
