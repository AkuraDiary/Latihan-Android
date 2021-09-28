package com.example.submission3belajarfundamentalaplikasiandroid.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserList(): LiveData<List<User>>

    @Query("SELECT * from user_table WHERE login = :username")
    fun getUserDetail(username: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(model: User): Int

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserListProvider(): Cursor

    //@Query("SELECT * from user_table ORDER BY login ASC")
    //fun getWidgetList(): List<GithubUser>
}