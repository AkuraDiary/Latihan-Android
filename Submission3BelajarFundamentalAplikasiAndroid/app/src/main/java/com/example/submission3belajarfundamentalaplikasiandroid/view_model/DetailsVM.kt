package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission3belajarfundamentalaplikasiandroid.data.UserFavoriteRepos
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UserDao
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UsrDatabase
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import kotlinx.coroutines.launch

class DetailsVM(app : Application) : AndroidViewModel(app)  {
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    private var userDao: UserDao = UsrDatabase.getDatabase(app).userDao()
    private var userFavoriteRepos: UserFavoriteRepos = UserFavoriteRepos(userDao)

    init {
        userFavoriteRepos = UserFavoriteRepos(userDao)
    }

    fun data(username: String): LiveData<ResourceStats<User>> = userFavoriteRepos.getDetailUser(username)


    fun addFavorite(user: User) = viewModelScope.launch {
        userFavoriteRepos.insert(user)
    }

    fun removeFavorite(user: User) = viewModelScope.launch {
        userFavoriteRepos.delete(user)
    }

    fun setForDetails(userID:String){
        if(userUsername.value == userID){
            return
        }
        userUsername.value = userID
    }
    val isFavorite: LiveData<Boolean> = userFavoriteRepos.isFavorite
}