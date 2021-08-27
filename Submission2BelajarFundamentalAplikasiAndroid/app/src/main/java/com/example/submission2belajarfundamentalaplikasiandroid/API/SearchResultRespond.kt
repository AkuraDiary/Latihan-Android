package com.example.submission2belajarfundamentalaplikasiandroid.API

import android.os.Parcelable
import com.example.submission2belajarfundamentalaplikasiandroid.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultRespond(
    val total : String,
    val incomplete_reslt: Boolean? = null,
    val items_res : List<User>
):Parcelable
