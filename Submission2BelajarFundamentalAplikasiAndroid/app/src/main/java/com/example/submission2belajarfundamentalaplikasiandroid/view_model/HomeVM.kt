package com.example.submission2belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission2belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission2belajarfundamentalaplikasiandroid.user.User

class HomeVM : ViewModel(){
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    val searchRes : LiveData<ResourceStats<List<User>>> = Transformations
        .switchMap(userUsername){
            UserRetrofit.searchForUser(it)
        }

    fun setForSearch(Query : String){
        if(userUsername.value == Query){
            return
        }
        userUsername.value = Query
    }
}