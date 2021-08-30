package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.util.Log
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats

object UserRetrofit {

    fun searchForUsers(Query: String) = liveData(Dispatchers.IO) {
        Log.d("User Retrofit", "search for user")
        emit(ResourceStats.onLoading(null))

        try {
            val userSearch = RConfig.API_Client.searchForUsers(Query)
            emit(ResourceStats.onSuccess(userSearch.items_res))
        } catch (e: Exception){
            emit(ResourceStats.onError(null, e.message ?: "Error occurred while searching for user"))
        }
    }

    fun getUserDetail(Username: String) = liveData(Dispatchers.IO) {
        emit(ResourceStats.onLoading(null))
        try{
            val userDetails = RConfig.API_Client.userDetails(Username)
            emit(ResourceStats.onSuccess(userDetails))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user detail"))
        }
    }

    fun getUserFollowers(Username: String) = liveData(Dispatchers.IO){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollower = RConfig.API_Client.userFollowers(Username)
            emit(ResourceStats.onSuccess(userFollower))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user followers"))
        }
    }

    fun getUserFollowing(Username:String) = liveData(Dispatchers.IO){
        emit(ResourceStats.onLoading(null))
        try{
            val userFollowing = RConfig.API_Client.userFollowings(Username)
            emit(ResourceStats.onSuccess(userFollowing))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user following"))
        }
    }

}