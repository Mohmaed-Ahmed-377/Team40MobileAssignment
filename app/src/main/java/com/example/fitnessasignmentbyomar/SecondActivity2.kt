package com.example.fitnessasignmentbyomar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.TextureView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)

        val date = intent.getStringExtra("Date")
        val tot = intent.getIntExtra("total",0)
        val date_txt= findViewById<TextView>(R.id.datee)

        val total_txt= findViewById<TextView>(R.id.total)
        date_txt.text= "Date: ${date}"
        total_txt.text= "total: ${tot}"



    }

}