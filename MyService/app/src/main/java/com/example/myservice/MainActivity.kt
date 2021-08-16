package com.example.myservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myservice.MyBoundService.MyBinder
class MainActivity : AppCompatActivity() {
    private var mServiceBound = false
    private var mBoundService = MyBoundService()
    
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val mBinder = service as MyBinder
            mBoundService = mBinder.getService
            mServiceBound = true
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
        }
        
    }

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
            val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
            bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)

        }

        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service) //variable tombolnya
        btnStopBoundService.setOnClickListener{//method jika diklik
            unbindService(mServiceConnection)

        }
    }
}
