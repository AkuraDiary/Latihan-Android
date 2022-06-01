package com.asthiseta.myticketingapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val seatView = findViewById<SeatsView>(R.id.seatsView)
        val button = findViewById<Button>(R.id.finishButton)

        button.setOnClickListener {
            seatView.seat?.let {
                Toast.makeText(this, "Kursi anda nomor ${it.name}", Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(this, "Anda belum memilih kursi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}