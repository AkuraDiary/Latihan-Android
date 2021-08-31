package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RConfig {

    //private const val MY_GITHUB_API_KEY = "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"//replace the github API
    //private const val githubUrl = "https://api.github.com"

    private val client by lazy{
        Log.d("client", "build")
        OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val origin = chain.request()
                val reqBuilder = origin.newBuilder()
                    . header("Authorization", "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")
                val clientRequest = reqBuilder.build()
                Log.d("okhttpclient", "proceed $clientRequest")
                chain.proceed(clientRequest)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .pingInterval(1, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit_Builder: Retrofit.Builder by lazy{
        Log.d("retrofit", "build $client")
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .callFactory{client.newCall(it)}
    }

    val API_Client : ClientAPI by lazy{
        Log.d("CLIENT API", "Build ${retrofit_Builder.build()}")
        Log.d("CLIENT API", "Build ${retrofit_Builder.client(client)}")
        retrofit_Builder.build().create(ClientAPI::class.java)
    }
}