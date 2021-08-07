package com.example.submission1belajarfundamentalaplikasiandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.submission1belajarfundamentalaplikasiandroid.databinding.ActivityDetailUserBinding


class DetailUser : AppCompatActivity() {
    companion object{
        const val EXTRA_USER_DATA = "extra user data"
    }

    private lateinit var userDetailBind: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userParcelable = intent.getParcelableExtra<Person>(EXTRA_USER_DATA)

        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.back_arrow)
            setDisplayHomeAsUpEnabled(true)
            title = userParcelable?.username
            elevation = 0f
        }

        userDetailBind = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(userDetailBind.root)

        Glide.with(this)
            .load(userParcelable?.avatar)
            .into(userDetailBind.detailAvatar)

        userDetailBind.apply{
            detailName.text = userParcelable?.name
            detailCompany.text = userParcelable?.company
            detailLocation.text = userParcelable?.location

            detailJumlahRepo.text = userParcelable?.repository
            detailJumlahFollower.text = userParcelable?.followers
            detailJumlahFollowing.text = userParcelable?.following
        }
    }
    override fun onSupportNavigateUp(): Boolean{
        onBackPressed()
        return  super.onSupportNavigateUp()
    }
}