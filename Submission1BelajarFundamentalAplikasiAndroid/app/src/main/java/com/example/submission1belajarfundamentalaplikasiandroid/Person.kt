package com.example.submission1belajarfundamentalaplikasiandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person (
    var avatar: Int?,
    var username: String?,
    var name: String?,
    var location: String?,
    var repository: String?,
    var company: String?,
    var followers: String?,
    var following: String?
    ):Parcelable
