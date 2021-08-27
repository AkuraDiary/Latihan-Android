package com.example.submission2belajarfundamentalaplikasiandroid.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RConfig {

    private const val MY_GITHUB_API_KEY = "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"//replace the github API
    private const val githubUrl = "https://api.github.com"

    private val Client by lazy{
        OkHttpClient.Builder()
            .addInterceptor{
                chain ->
                val origin = chain.request()
                val reqBuilder = origin.newBuilder()
                    . header("Authorization", MY_GITHUB_API_KEY)
                val clientRequest = reqBuilder.build()
                chain.proceed(clientRequest)
            }
            . connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private val retrofit_Builder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(githubUrl)
            .client(Client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val API_Client : ClientAPI by lazy{
        retrofit_Builder.build().create(ClientAPI::class.java)
    }
}