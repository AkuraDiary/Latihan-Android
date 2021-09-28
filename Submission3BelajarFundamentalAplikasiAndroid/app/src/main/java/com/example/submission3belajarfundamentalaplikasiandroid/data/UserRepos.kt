package com.example.submission3belajarfundamentalaplikasiandroid.data

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.submission3belajarfundamentalaplikasiandroid.api.RetrofitConfiguration
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UserDao
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats

object UserRepos {

    fun searchUsers(query: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading((null)))
        try {
            val userSearch = RetrofitConfiguration.CLIENT_API.searchForUsers(query)
            emit(ResourceStats.onSuccess(userSearch.items))
        } catch (e: Exception) {
            emit(ResourceStats.onError(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun getUserFollowers(username: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try {
            emit(ResourceStats.onSuccess(RetrofitConfiguration.CLIENT_API.getUserFollowers(username)))
        } catch (e: Exception) {
            emit(ResourceStats.onError(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun getUserFollowing(username: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try {
            emit(ResourceStats.onSuccess(RetrofitConfiguration.CLIENT_API.getUserFollowings(username)))
        } catch (e: Exception) {
            emit(ResourceStats.onError(null, e.message ?: "Error Detected: ${e.localizedMessage}"))
        }
    }

    fun  getFavorite(userDao: UserDao) = userDao.getUserList()

}