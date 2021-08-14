package com.example.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //start service
        val btnStartService = findViewById<Button>(R.id.btn_start_service) //variable tombolnya
        btnStartService.setOnClickListener{//method jika diklik
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
        }

        //Job intent service
        val btnStartJobIntentService = findViewById<Button>(R.id.btn_start_job_intent_service) //variable tombolnya
        btnStartJobIntentService.setOnClickListener{//method jika diklik
            val mStartIntentService = Intent(this, MyJobIntentService::class.java)
            mStartIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
            MyJobIntentService.enqueueWork(this, mStartIntentService)
        }

        //bound service
        val btnStartBoundService = findViewById<Button>(R.id.btn_start_bound_service) //variable tombolnya
        btnStartBoundService.setOnClickListener{//method jika diklik

        }

        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service) //variable tombolnya
        btnStopBoundService.setOnClickListener{//method jika diklik

        }

    }
}