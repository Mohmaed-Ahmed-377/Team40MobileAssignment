package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class ActivityEntry(val name: String, val duration: Int) {
    override fun toString(): String {
        return "$name - $duration mins"
    }
}
class MainActivity : AppCompatActivity() {

    private val fullList = ArrayList<ActivityEntry>()
    private val displayList = ArrayList<ActivityEntry>()

    private lateinit var adapter: ArrayAdapter<ActivityEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etActivityName)
        val etDuration = findViewById<EditText>(R.id.etDuration)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnFilter = findViewById<Button>(R.id.btnFilter)
        val btnShowAll = findViewById<Button>(R.id.btnShowAll)
        val listView = findViewById<ListView>(R.id.listView)

        //  Adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter

        // ADD Button Logic
        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val durationStr = etDuration.text.toString()
            if (name.isNotEmpty() && durationStr.isNotEmpty()) {
                val duration = durationStr.toInt()
                val newActivity = ActivityEntry(name, duration)
                // Add to our main storage list
                fullList.add(newActivity)
                // Update the display list (showing all by default after add)
                refreshList(fullList)
                // Clear inputs
                etName.text.clear()
                etDuration.text.clear()
            } else {
                Toast.makeText(this, "Please enter both name and duration", Toast.LENGTH_SHORT).show()
            }
        }


    }

    // update the ListView
    private fun refreshList(newList: List<ActivityEntry>) {
        displayList.clear()
        displayList.addAll(newList)
        adapter.notifyDataSetChanged()
    }
}
