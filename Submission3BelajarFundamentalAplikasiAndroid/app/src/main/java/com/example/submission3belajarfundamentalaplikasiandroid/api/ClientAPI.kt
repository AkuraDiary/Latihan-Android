package com.example.submission3belajarfundamentalaplikasiandroid.api

import com.example.submission3belajarfundamentalaplikasiandroid.user.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientAPI {
    @GET("search/users")
    suspend fun searchForUsers(
        @Query("q") q: String?
    ): SearchResultResponds

    @GET("users/{username}")
    suspend fun setUserDetails(
        @Path("username") username: String?
    ): User

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username:String?
    ): List<User>

    @GET("users/{username}/following")
    suspend fun getUserFollowings(
        @Path("username") username:String?
    ): List<User>
}