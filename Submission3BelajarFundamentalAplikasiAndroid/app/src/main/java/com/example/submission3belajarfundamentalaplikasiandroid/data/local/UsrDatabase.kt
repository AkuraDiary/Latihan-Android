package com.example.submission3belajarfundamentalaplikasiandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission3belajarfundamentalaplikasiandroid.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsrDatabase: RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: UsrDatabase? = null

        fun getDatabase(context: Context): UsrDatabase {
            val mInstance = INSTANCE
            if (mInstance != null)
                return mInstance

            synchronized(UsrDatabase::class){
                val mbuilder = Room.databaseBuilder(
                    context.applicationContext, UsrDatabase::class.java, "database_github"
                ).build()
                INSTANCE = mbuilder
                return mbuilder
            }
        }
    }
    abstract fun userDao(): UserDao
}