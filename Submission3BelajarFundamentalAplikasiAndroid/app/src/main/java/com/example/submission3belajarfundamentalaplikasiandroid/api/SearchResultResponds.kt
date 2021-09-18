package com.example.submission3belajarfundamentalaplikasiandroid.api

import android.os.Parcelable
import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultResponds(
    val total_count : String,
    val incomplete_result: Boolean? = null,
    val items : List<User>
):Parcelable
