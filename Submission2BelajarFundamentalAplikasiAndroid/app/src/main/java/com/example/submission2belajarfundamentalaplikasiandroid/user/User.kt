package com.example.submission2belajarfundamentalaplikasiandroid.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    //nama variablenya harus sama dengan fieldnya di link api nya (kalau tidak datanya ngga masuk)
    val id : Int,
    val login : String,
    val avatar_url: String,
    val name: String?,
    val location: String?,
    val type: String?,
    val public_repos: Int,
    val followers: Int,
    val following : Int
):Parcelable
