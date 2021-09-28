package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submission3belajarfundamentalaplikasiandroid.data.UserRepos
import com.example.submission3belajarfundamentalaplikasiandroid.data.local.UsrDatabase
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

class FavoriteVM(app: Application) : AndroidViewModel(app) {
    val favData: LiveData<List<User>>

    init {
        val userDao = UsrDatabase.getDatabase(app).userDao()
        favData = UserRepos.getFavorite(userDao)
    }
}