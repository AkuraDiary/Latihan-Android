package com.example.submission3belajarfundamentalaplikasiandroid.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.submission3belajarfundamentalaplikasiandroid.api.RetrofitConfiguration
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UserDao
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import kotlinx.coroutines.Dispatchers

class UserFavoriteRepos(private val userDao: UserDao) {
    private var favorite: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailUser(username:String) = liveData(Dispatchers.IO){
        emit(ResourceStats.onLoading(null))
        val user = userDao.getUserDetail(username)
        if (user != null){
            favorite.postValue(true)
            emit(ResourceStats.onSuccess(user))
        } else {
            favorite.postValue(false)
            try {
                emit(ResourceStats.onSuccess(RetrofitConfiguration.CLIENT_API.setUserDetails(username)))
            } catch (e: Exception){
                emit(ResourceStats.onError(null, e.message ?: "Error"))
            }
        }
    }

    suspend fun insert(user: User){
        Log.d("insert user before : ", favorite.value.toString())
        userDao.insertUser(user)
        favorite.value = true
        Log.d("insert user after : ", favorite.value.toString())
    }

    suspend fun delete(user: User){
        Log.d("delete user before : ", favorite.value.toString())
        userDao.deleteUser(user)
        favorite.value = false
        Log.d("delete user after : ", favorite.value.toString())
    }
    val isFavorite: LiveData<Boolean> = favorite
}