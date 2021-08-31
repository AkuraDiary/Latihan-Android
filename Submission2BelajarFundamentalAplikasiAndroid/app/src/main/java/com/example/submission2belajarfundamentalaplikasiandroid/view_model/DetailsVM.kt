package com.example.submission2belajarfundamentalaplikasiandroid.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.submission2belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission2belajarfundamentalaplikasiandroid.user.User

class DetailsVM : ViewModel()  {
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    val dataDetail : LiveData<ResourceStats<User>> = Transformations
        .switchMap(userUsername) {
            Log.d("data detail", it)
            UserRetrofit.getUserDetail(it)
        }

    fun setForDetails(userID:String){
        if(userUsername.value == userID){
            return
        }
        userUsername.value = userID
    }
}