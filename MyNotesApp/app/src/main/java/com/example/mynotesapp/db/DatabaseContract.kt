package com.example.mynotesapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

internal class DatabaseContract {
    internal class NoteColumns : BaseColumns{
        companion object{
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }

    internal class Databasehelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
        companion object{
            private const val DATABASE_NAME = "dbnoteapp"
            private const val DATABASE_VERSION = 1
            private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE ${NoteColumns.TABLE_NAME}" +
                    " (${NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ${NoteColumns.TITLE} TEXT NOT NULL," +
                    " ${NoteColumns.DESCRIPTION} TEXT NOT NULL," +
                    " ${NoteColumns.DATE} TEXT NOT NULL)"
        }

        override fun onCreate(db: SQLiteDatabase){
            db.execSQL(SQL_CREATE_TABLE_NOTE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion: Int){
            db.execSQL("DROP TABLE IF EXISTS ${NoteColumns.TABLE_NAME}")
            onCreate(db)
        }
    }
}