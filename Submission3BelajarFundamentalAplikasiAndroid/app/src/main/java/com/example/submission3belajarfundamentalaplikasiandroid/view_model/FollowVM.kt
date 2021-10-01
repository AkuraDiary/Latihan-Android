package com.example.submission3belajarfundamentalaplikasiandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.submission3belajarfundamentalaplikasiandroid.data.UserRepos
import com.example.submission3belajarfundamentalaplikasiandroid.others.FollowView
import com.example.submission3belajarfundamentalaplikasiandroid.others.ResourceStats
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

class FollowVM : ViewModel(){

    private val userUsername: MutableLiveData<String> = MutableLiveData()
    private lateinit var type: FollowView

    val dataFollows : LiveData<ResourceStats<List<User>>> = Transformations
        .switchMap(userUsername){
            when(type){
                FollowView.FOLLOWERS -> {
                    UserRepos.getUserFollowers(it)
                }

                FollowView.FOLLOWINGS-> {
                    UserRepos.getUserFollowing(it)
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