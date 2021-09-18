package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission3belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

class DetailsVM : ViewModel()  {
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    val dataDetail : LiveData<ResourceStats<User>> = Transformations
        .switchMap(userUsername) {
            UserRetrofit.getUserDetail(it)
        }

    fun setForDetails(userID:String){
        if(userUsername.value == userID){
            return
        }
        userUsername.value = userID
    }
}