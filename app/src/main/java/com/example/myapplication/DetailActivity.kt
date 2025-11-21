package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.AppDatabase

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvDate = findViewById<TextView>(R.id.tvDetailDate)
        val tvTotalDuration = findViewById<TextView>(R.id.tvTotalDuration)

        val selectedDate = intent.getStringExtra("EXTRA_DATE")
        tvDate.text = "Date: $selectedDate"

        if (selectedDate != null) {
            val db = AppDatabase.getDatabase(this)

            db.activityDao().getTotalDurationByDate(selectedDate).observe(this) { total ->
                val duration = total ?: 0
                tvTotalDuration.text = "Total Duration: $duration mins"
            }
        } else {
            tvTotalDuration.text = "Error: No date provided"
        }
    }
}
