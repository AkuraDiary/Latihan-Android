package com.example.submission3belajarfundamentalaplikasiandroid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.submission3belajarfundamentalaplikasiandroid.api.RetrofitConfiguration
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UserDao
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import kotlinx.coroutines.Dispatchers

class UserFavoriteRepos(private val userDao: UserDao) {
    private val favorite: MutableLiveData<Boolean> = MutableLiveData()

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
        userDao.insertUser(user)
        favorite.value = true
    }

    suspend fun delete(user: User){
        userDao.deleteUser(user)
        favorite.value = false
    }

    val isFavorite: LiveData<Boolean> = favorite

}