package com.example.submission2belajarfundamentalaplikasiandroid.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    //nama variablenya harus sama dengan fieldnya di link api nya (kalau tidak datanya ngga masuk)
    var id : Int,
    var login : String,
    var avatar_url: String,
    var name: String?,
    var location: String?,
    var type: String?,
    var public_repos: Int,
    var followers: Int,
    var following : Int
):Parcelable
