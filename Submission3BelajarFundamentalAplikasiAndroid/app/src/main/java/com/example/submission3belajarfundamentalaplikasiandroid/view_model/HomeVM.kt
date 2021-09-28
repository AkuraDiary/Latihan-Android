package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission3belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission3belajarfundamentalaplikasiandroid.data.UserRepos
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

class HomeVM : ViewModel(){
    private val userUsername: MutableLiveData<String> = MutableLiveData()
    //private lateinit var bindingHome: FragmentHomeBinding

    val searchResult : LiveData<ResourceStats<List<User>>> = Transformations
        .switchMap(userUsername){
            //UserRetrofit.searchUsers(it)
            UserRepos.searchUsers(it)
        }

    fun setForSearch(Query : String){
        if(userUsername.value == Query){
            return
        }
        userUsername.value = Query
    }
}