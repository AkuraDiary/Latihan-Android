package com.example.submission1belajarfundamentalaplikasiandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView


class DetailUser : AppCompatActivity() {
    companion object{
       const val EXTRA_DATA = "0"
    }

    private lateinit var detailAvatar : ImageView
    private lateinit var detailUsername : TextView
    private lateinit var detailNama : TextView
    private lateinit var detailLocation : TextView
    private lateinit var detailRepository : TextView
    private lateinit var detailCompany : TextView
    private lateinit var detailFollowers : TextView
    private lateinit var detailFollowing : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailAvatar = findViewById(R.id.detail_avatar)
        detailUsername = findViewById(R.id.detail_username)
        detailNama = findViewById(R.id.detail_name)
        detailLocation = findViewById(R.id.detail_location)
        detailRepository = findViewById(R.id.detail_repository)
        detailCompany = findViewById(R.id.detail_company)
        detailFollowers = findViewById(R.id.detail_followers)
        detailFollowing = findViewById(R.id.detail_following)


        val actionbar = supportActionBar
        actionbar!!.title= "Detail User"


        //val ListUser: Person? = intent.getParcelableExtra<Person>(EXTRA_DATA)
/*
        detailAvatar.setImageResource()
        detailUsername.text = ListUser?.
        detailNama.text = ListUser?.name.toString()
        detailLocation.text =
        detailRepository.text = getString(R.string.repository, ListUser.repository)
        detailCompany.text = getString(R.string.company, ListUser.company)
        detailFollowers.text = getString(R.string.followers, ListUser.followers)
        detailFollowing.text = getString(R.string.following, ListUser.following)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)*/

    }
}