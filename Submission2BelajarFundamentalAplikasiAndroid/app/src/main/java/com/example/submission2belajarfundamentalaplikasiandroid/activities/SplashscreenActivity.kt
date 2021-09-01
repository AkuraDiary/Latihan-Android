package com.example.submission2belajarfundamentalaplikasiandroid.activities

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.submission2belajarfundamentalaplikasiandroid.databinding.ActivitySplashscreenBinding
import java.lang.Exception

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var bindingSplash : ActivitySplashscreenBinding
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindingSplash = ActivitySplashscreenBinding.inflate(layoutInflater)

        setContentView(bindingSplash.root)

        // animasi progress bar
        val progressBar = bindingSplash.progressBar
        progressBar.max = 100
        val barAnimation = ObjectAnimator.ofInt(progressBar, "progress", 100)
        barAnimation.duration = 2500
        // start the progress bar animation
        barAnimation.start()

        handler = Handler()

        handler.postDelayed({
            val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        /*val background = object : Thread(){
            override fun run(){
                try{
                    /* do some code here while loading */
                    sleep(3000)
                    // continue to the next activity
                    val intent = Intent(this@SplashscreenActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()*/
    }
}