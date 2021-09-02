package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {


    private val client by lazy{
        Log.d("client", "build")
        OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Change with your github token")
                val request = requestBuilder.build()
                Log.d("okhttpclient", "proceed $request")
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

    }

    private val retrofitBuilder: Retrofit.Builder by lazy{
        Log.d("retrofit", "build client :  $client")
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiClient : ApiClient by lazy{
        Log.d("CLIENT API", "Building retrofit builder : ${retrofitBuilder.build()}")
        //Log.d("CLIENT API", "Build ${retrofit_Builder.client(client)}")
        retrofitBuilder.build().create(ApiClient::class.java)
    }
}