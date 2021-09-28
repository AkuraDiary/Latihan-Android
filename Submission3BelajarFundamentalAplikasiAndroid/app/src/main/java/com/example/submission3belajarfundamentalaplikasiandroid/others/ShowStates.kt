package com.example.submission3belajarfundamentalaplikasiandroid.others

import android.view.View
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentFavoriteBinding
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentFollowBinding
import com.example.submission3belajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding

interface ShowStates {
    fun homeLoading(bindingHome: FragmentHomeBinding): Int? = null
    fun homeSuccess(bindingHome: FragmentHomeBinding): Int? = null
    fun homeError(bindingHome: FragmentHomeBinding, message: String?): Int? = null

    fun followLoading(bindingFollow: FragmentFollowBinding): Int? = null
    fun followSuccess(bindingFollow: FragmentFollowBinding): Int? = null
    fun followError(bindingFollow: FragmentFollowBinding, message: String?): Int? = null

    fun favoriteLoading(bindingFavorite : FragmentFavoriteBinding): Int? = null
    fun favoriteSuccess(bindingFavorite: FragmentFavoriteBinding): Int? = null
    fun favoriteError(bindingFavorite: FragmentFavoriteBinding, message: String?): Int? = null

    val gone: Int
        get() = View.GONE

    val visible: Int
        get() = View.VISIBLE
}