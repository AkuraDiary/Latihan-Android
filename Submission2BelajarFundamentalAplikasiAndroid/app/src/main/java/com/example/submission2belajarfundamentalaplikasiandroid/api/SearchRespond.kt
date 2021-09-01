package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.os.Parcelable
import com.example.submission2belajarfundamentalaplikasiandroid.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRespond(
    val total_count : String,
    val incomplete_result: Boolean? = null,
    val items : List<User>
):Parcelable
