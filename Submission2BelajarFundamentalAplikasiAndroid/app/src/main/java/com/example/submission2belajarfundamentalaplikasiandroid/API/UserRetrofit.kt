package com.example.submission2belajarfundamentalaplikasiandroid.API

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats

object UserRetrofit {
    fun searchForUser(query: String) = liveData(Dispatchers.IO) {
        emit(ResourceStats.onLoading(null))
        try {
            val userSearch = RConfig.API_Client.searchForUsers(query)
            emit(ResourceStats.onSuccess(userSearch.items_res))
        } catch (exception: Exception){
            emit(ResourceStats.onError(null, exception.message ?: "Error"))
        }
    }


}