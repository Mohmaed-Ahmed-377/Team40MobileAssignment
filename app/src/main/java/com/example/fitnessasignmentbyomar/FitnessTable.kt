package com.example.fitnessasignmentbyomar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "FitnessTable"
)
data class FitnessTable(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val name: String,
    val duration: Int,
    val date: String
)
