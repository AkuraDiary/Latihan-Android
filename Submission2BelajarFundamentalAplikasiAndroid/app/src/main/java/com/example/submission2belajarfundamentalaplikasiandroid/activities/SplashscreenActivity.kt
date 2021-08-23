package com.example.submission2belajarfundamentalaplikasiandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.ActivitySplashscreenBinding
import java.lang.Exception

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding_splash : ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_splash = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding_splash.root)

        val background = object : Thread(){
            override fun run(){
                try{
                    // do some code here while loading
                    sleep(3000)
                    // continue to the next activity
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}