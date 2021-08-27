package com.example.submission2belajarfundamentalaplikasiandroid.api
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats

object UserRetrofit {

    fun searchForUser(Query: String) = liveData(Dispatchers.IO) {
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
            emit(ResourceStats.onSuccess(RConfig.API_Client.userDetails(Username)))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user detail"))
        }
    }

    fun getUserFollowers(Username: String) = liveData(Dispatchers.IO){
        emit(ResourceStats.onLoading(null))
        try{
            emit(ResourceStats.onSuccess(RConfig.API_Client.userDetails(Username)))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user followers"))
        }
    }

    fun getUserFollowing(Username:String) = liveData(Dispatchers.IO){
        emit(ResourceStats.onLoading(null))
        try{
            emit(ResourceStats.onSuccess(RConfig.API_Client.userDetails(Username)))
        }catch (e:Exception){
            emit(ResourceStats.onError(null, e.message?: "Error occurred while get user following"))
        }
    }

}