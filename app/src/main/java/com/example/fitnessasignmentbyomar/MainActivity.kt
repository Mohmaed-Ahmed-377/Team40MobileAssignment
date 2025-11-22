package com.example.fitnessasignmentbyomar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class Item(
    val name: String,
    val duration: Int,
    val date: String
)


class MainActivity : AppCompatActivity() {
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filter_btn = findViewById<Button>(R.id.filter)
        val add_btn = findViewById<Button>(R.id.add)
        val show_btn = findViewById<Button>(R.id.show)
        val name_txt = findViewById<EditText>(R.id.name)
        val duration_txt = findViewById<EditText>(R.id.duratoin)
        val list_items = findViewById<ListView>(R.id.list)
        val datepick = findViewById<DatePicker>(R.id.datePicker)
        val dp = FitnessDataBase.getInstance(this)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        list_items.adapter = adapter

        add_btn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val name = name_txt.text.toString()
                val dur = duration_txt.text.toString().toIntOrNull()
                val dat = "${datepick.dayOfMonth}-${datepick.month + 1}-${datepick.year}"

                if (name.isNotEmpty() && dur != null) {
                    dp.fitnessDao().insert(FitnessTable(name = name, duration = dur, date = dat))
                    val s = "$name ${dur} min $dat"

                    withContext(Dispatchers.Main) {
                        adapter.add(s)
                    }
                }
            }
        }

        show_btn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val fitnesshistory = dp.fitnessDao().get_all().toMutableList()
                val list = fitnesshistory.map { "${it.name} ${it.duration} min ${it.date}" }

                withContext(Dispatchers.Main) {
                    adapter.clear()
                    adapter.addAll(list)
                }
            }
        }

        filter_btn.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val day = datepick.dayOfMonth
                val month = datepick.month + 1
                val year = datepick.year
                val d = "$day-$month-$year"
                val filtered = dp.fitnessDao().get_filter(date = d).toMutableList()
                val list = filtered.map { "${it.name} ${it.duration} min ${it.date}" }

                withContext(Dispatchers.Main) {
                    adapter.clear()
                    adapter.addAll(list)
                }
            }
        }

        list_items.setOnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position).toString()
            val date = extractDate(item)

            lifecycleScope.launch(Dispatchers.IO) {
                val filtered = dp.fitnessDao().get_filter(date = date).toMutableList()
                val tot = filtered.sumOf { it.duration }

                withContext(Dispatchers.Main) {
                    val intent = Intent(this@MainActivity, SecondActivity2::class.java)
                    intent.putExtra("Date", date)
                    intent.putExtra("total", tot)
                    startActivity(intent)
                }
            }
        }
    }
}

private fun MainActivity.extractDate(item: String): String {
    val x = item.split(" ")
    return x.last()
}

