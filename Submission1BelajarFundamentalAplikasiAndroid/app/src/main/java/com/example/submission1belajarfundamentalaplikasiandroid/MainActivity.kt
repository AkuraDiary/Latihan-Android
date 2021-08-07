package com.example.submission1belajarfundamentalaplikasiandroid

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var listuser: ListView
    lateinit var Adapter : UserAdapter
    private lateinit var  Users: ArrayList<Person>
    private lateinit var User_avatar: TypedArray
    private lateinit var User_username:Array<String>
    private lateinit var User_nama:Array<String>
    private lateinit var User_lokasi:Array<String>
    private lateinit var User_repositori:Array<String>
    private lateinit var User_company:Array<String>
    private lateinit var User_followers:Array<String>
    private lateinit var User_following:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionbar = supportActionBar
        actionbar!!.title= "List User"

        getData()

        Adapter  = UserAdapter(this, addData())
        listuser = findViewById(R.id.rv_list)
        listuser.adapter = Adapter
/*
        listuser.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _->
            val listDataUser = Person(0,"","", "", "", "", "", "")
            listDataUser.avatar = User_avatar.getResourceId(position, position)
            listDataUser.username = User_username[position]
            listDataUser.name = User_nama[position]
            listDataUser.location = User_lokasi[position]
            listDataUser.repository = User_repositori[position]
            listDataUser.company = User_company[position]
            listDataUser.followers = User_followers[position]
            listDataUser.following = User_following[position]

            val intent = Intent(this@MainActivity, DetailUser::class.java)
            intent.putExtra(DetailUser.EXTRA_DATA, listDataUser)

            this@MainActivity.startActivity(intent)
            Toast.makeText(this@MainActivity, Users[position].name, Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun getData(){
        User_avatar = resources.obtainTypedArray(R.array.avatar)
        User_username = resources.getStringArray(R.array.username)
        User_nama = resources.getStringArray(R.array.name)
        User_lokasi = resources.getStringArray(R.array.location)
        User_repositori = resources.getStringArray(R.array.repository)
        User_company = resources.getStringArray(R.array.company)
        User_followers = resources.getStringArray(R.array.followers)
        User_following = resources.getStringArray(R.array.following)
    }

    private fun addData(): ArrayList<Person>{
        Users = ArrayList()
        for (i in User_nama.indices){
            val akun = Person()
            akun.avatar = User_avatar.getResourceId(i, -1)
            akun.name = User_nama[i]
            akun.company = User_company[i]
            akun.location = User_lokasi[i]
            Users.add(akun)
        }
        return Users
    }
}