package com.example.submission2belajarfundamentalaplikasiandroid.api

import com.example.submission2belajarfundamentalaplikasiandroid.user.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientAPI {
    @GET("search/users")
    suspend fun searchForUsers(
        @Query("q") q:String?
    ):SearchResultRespond

    @GET("users/{username}")
    suspend fun userDetails(
        @Path("username") username: String?
    )

    @GET("users/{username}/followers")
    suspend fun userFollowers(
        @Path("username") username:String?
    ): List<User>

    @GET("users/{username}/following")
    suspend fun userFollowings(
        @Path("username") username:String?
    ): List<User>
}