package com.example.submission2belajarfundamentalaplikasiandroid.api

import androidx.lifecycle.liveData
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats
import kotlinx.coroutines.Dispatchers

object UserRetrofit {

    fun searchUsers(Query: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try {
            val userSearch = RetrofitConfiguration.CLIENT_API.searchForUsers(Query)
            emit(ResourceStats.onSuccess(userSearch.items))
        } catch (exception: Exception){
            emit(ResourceStats.onError(null, exception.message ?: "Error occurred while searching for user"))
        }
    }

    fun getUserDetail(Username: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try{
            val userDetails = RetrofitConfiguration.CLIENT_API.setUserDetails(Username)
            emit(ResourceStats.onSuccess(userDetails))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user detail"))
        }
    }

    fun getUserFollowers(Username: String) = liveData(Dispatchers.Default){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollower = RetrofitConfiguration.CLIENT_API.getUserFollowers(Username)
            emit(ResourceStats.onSuccess(userFollower))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user followers"))
        }
    }

    fun getUserFollowing(Username:String) = liveData(Dispatchers.Default){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollowing = RetrofitConfiguration.CLIENT_API.getUserFollowings(Username)
            emit(ResourceStats.onSuccess(userFollowing))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user following"))
        }
    }

}