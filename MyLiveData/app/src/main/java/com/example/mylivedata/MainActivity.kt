package com.example.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mLiveDataTimerViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }

    private fun subscribe(){
        val elapsedTimeObserver = Observer<Long?>{ aLong ->
            val newText = this@MainActivity.resources.getString(R.string.seconds)
            activityMainBinding.timerTextview.text = newText
        }

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}