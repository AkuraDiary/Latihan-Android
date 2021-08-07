package com.example.submission1belajarfundamentalaplikasiandroid

import android.content.Context
import android.content.res.TypedArray

object DummyData {
    private lateinit var daftarAvatar: TypedArray
    private lateinit var daftarUsername: Array<String>
    private lateinit var daftarNama: Array<String>
    private lateinit var daftarLokasi: Array<String>
    private lateinit var daftarCompany: Array<String>
    private lateinit var daftarRepository: Array<String>
    private lateinit var daftarFollower: Array<String>
    private lateinit var daftarFollowing: Array<String>

    private fun prepareTheData(context: Context){
        daftarAvatar = context.resources.obtainTypedArray(R.array.avatar)
        daftarUsername = context.resources.getStringArray(R.array.username)
        daftarNama = context.resources.getStringArray(R.array.name)
        daftarLokasi = context.resources.getStringArray(R.array.location)
        daftarCompany = context.resources.getStringArray(R.array.company)
        daftarRepository = context.resources.getStringArray(R.array.repository)
        daftarFollower = context.resources.getStringArray(R.array.followers)
        daftarFollowing = context.resources.getStringArray(R.array.following)
    }

    fun getUserData(context: Context):ArrayList<Person>{
        val daftarDataUser: ArrayList<Person> = ArrayList()
        prepareTheData(context)

        for(position in daftarUsername.indices){
            val user = Person(
                avatar = daftarAvatar.getResourceId(position, 0),
                username = daftarUsername[position],
                name = daftarNama[position],
                company = daftarCompany[position],
                location = daftarLokasi[position],
                repository = daftarRepository[position],
                followers = daftarFollower[position],
                following = daftarFollowing[position]
            )
            daftarDataUser.add(user)
        }
        return daftarDataUser
    }
}