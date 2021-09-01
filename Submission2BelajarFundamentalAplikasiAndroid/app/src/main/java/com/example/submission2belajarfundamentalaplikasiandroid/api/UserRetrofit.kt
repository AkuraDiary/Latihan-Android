package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.util.Log
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats

object UserRetrofit {

    fun searchUsers(Query: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        Log.d("Loading User Retrofit", ResourceStats.onLoading(null).toString())
        try {
            val userSearch = RetrofitConfig.apiClient.searchForUsers(Query)
            Log.d("User Retrofit Search for Users", "searching for $Query")
            Log.d("User Retrofit search for User obj:", RetrofitConfig.apiClient.toString())
            Log.d("User Retrofit search for User", "emitting user resource $userSearch")
            emit(ResourceStats.onSuccess(userSearch.items))
        } catch (exception: Exception){
            Log.d("User Retrofit search for user", "error $exception")
            emit(ResourceStats.onError(null, exception.message ?: "Error occurred while searching for user"))
        }
    }

    fun getUserDetail(Username: String) = liveData(Dispatchers.Default) {
        emit(ResourceStats.onLoading(null))
        try{
            val userDetails = RetrofitConfig.apiClient.userDetails(Username)
            emit(ResourceStats.onSuccess(userDetails))
            Log.d("User Retrofit", "get user detail $userDetails")
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user detail"))
        }
    }

    fun getUserFollowers(Username: String) = liveData(Dispatchers.Default){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollower = RetrofitConfig.apiClient.userFollowers(Username)
            emit(ResourceStats.onSuccess(userFollower))
            Log.d("User Retrofit", "get follower")
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user followers"))
        }
    }

    fun getUserFollowing(Username:String) = liveData(Dispatchers.Default){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollowing = RetrofitConfig.apiClient.userFollowings(Username)
            emit(ResourceStats.onSuccess(userFollowing))
            Log.d("User Retrofit", "get following")
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user following"))
        }
    }

}