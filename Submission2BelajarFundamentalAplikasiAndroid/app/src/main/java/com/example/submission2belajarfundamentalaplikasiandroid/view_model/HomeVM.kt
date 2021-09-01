package com.example.submission2belajarfundamentalaplikasiandroid.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission2belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission2belajarfundamentalaplikasiandroid.user.User

class HomeVM : ViewModel(){
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    val searchResult : LiveData<ResourceStats<List<User>>> = Transformations
        .switchMap(userUsername){
            Log.d("Search Res switchmap", UserRetrofit.searchUsers(it).toString())
            UserRetrofit.searchUsers(it)
        }

    fun setForSearch(Query : String){
        Log.d("HOME VM", "set for search $Query")
        if(userUsername.value == Query){
            Log.d("HOME VM setForSerSearch return", userUsername.value.toString())
            return
        }
        userUsername.value = Query
    }
}