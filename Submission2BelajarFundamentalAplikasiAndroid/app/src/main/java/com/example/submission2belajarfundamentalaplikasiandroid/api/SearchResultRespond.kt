package com.example.submission2belajarfundamentalaplikasiandroid.api

import android.os.Parcelable
import com.example.submission2belajarfundamentalaplikasiandroid.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultRespond(
    val total : String,
    val incomplete_reslt: Boolean? = null,
    val items_res : List<User>
):Parcelable
