package com.example.submission1belajarfundamentalaplikasiandroid


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1belajarfundamentalaplikasiandroid.databinding.ActivityMainBinding.inflate
import com.example.submission1belajarfundamentalaplikasiandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionbar = supportActionBar
        actionbar!!.title= "List User"

        mainBinding = inflate(layoutInflater)
        setContentView(mainBinding.root)

        val userDataAdapter = UserAdapter()
        userDataAdapter.setData(DummyData.getUserData(this))

        mainBinding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = userDataAdapter
        }
    }
}

