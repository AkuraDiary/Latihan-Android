package com.example.submission3belajarfundamentalaplikasiandroid.user

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import com.squareup.moshi.Json

@Entity(tableName = "user_table")
@Parcelize
data class User(
    //nama variablenya harus sama dengan fieldnya di link api nya (kalau tidak datanya ngga masuk)

    @PrimaryKey(autoGenerate = false)
    @field:Json(name = "id")
    val id : Int,

    @ColumnInfo(name = "login")
    @field:Json(name = "login")
    val login : String,

    @ColumnInfo(name = "avatar_url")
    @field:Json(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String?,

    @ColumnInfo(name = "company")
    @field:Json(name = "company")
    val company: String?,

    @ColumnInfo(name = "location")
    @field:Json(name = "location")
    val location: String?,

    @ColumnInfo(name = "type")
    @field:Json(name = "type")
    val type: String?,

    @ColumnInfo(name = "public_repos")
    @field:Json(name = "public_repos")
    val public_repos: Int,

    @ColumnInfo(name = "followers")
    @field:Json(name = "followers")
    val followers: Int,

    @ColumnInfo(name = "following")
    @field:Json(name = "following")
    val following : Int
):Parcelable
