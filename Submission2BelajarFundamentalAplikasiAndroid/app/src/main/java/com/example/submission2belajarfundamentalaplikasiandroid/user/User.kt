package com.example.submission2belajarfundamentalaplikasiandroid.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id : Int,
    val login : String,
    val avatar_url: String,
    val nama: String?,
    val lokasi: String?,
    val type: String?,
    val repos: Int,
    val folower: Int,
    val folowing : Int
):Parcelable
