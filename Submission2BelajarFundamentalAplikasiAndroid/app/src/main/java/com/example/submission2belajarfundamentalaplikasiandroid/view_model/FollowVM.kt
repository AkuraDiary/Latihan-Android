package com.example.submission2belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission2belajarfundamentalaplikasiandroid.api.UserRetrofit
import com.example.submission2belajarfundamentalaplikasiandroid.others.FollowView
import com.example.submission2belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission2belajarfundamentalaplikasiandroid.user.User

class FollowVM : ViewModel(){

    private lateinit var type: FollowView
    private val userUsername: MutableLiveData<String> = MutableLiveData()

    val dataFollows : LiveData<ResourceStats<Unit>> = Transformations
        .switchMap(userUsername){
            when(type){
                FollowView.FOLLOWERS -> {
                    UserRetrofit.getUserFollowers(it)
                }

                FollowView.FOLLOWINGS-> {
                    UserRetrofit.getUserFollowing(it)
                }
            }
        }

    fun setFollows(user:String, Type: FollowView){
        if(userUsername.value == user){
            return
        }
        userUsername.value = user
        type = Type
    }
}