package com.example.submission2belajarfundamentalaplikasiandroid.API

import androidx.lifecycle.liveData
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response.error
import kotlin.Result.Companion.success

object UserRetrofit {
    /*fun searchUsers(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val userSearch = RetrofitConfig.apiClient.searchUsers(query)
            emit(Resource.success(userSearch.items))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error"))
        }
    }*/
}