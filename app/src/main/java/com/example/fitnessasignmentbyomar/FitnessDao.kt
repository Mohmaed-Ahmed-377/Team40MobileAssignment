package com.example.fitnessasignmentbyomar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FitnessDao {
    @Insert
    suspend fun insert(fitnessTable: FitnessTable)

    @Query("Select * From FitnessTable Where FitnessTable.date ==:date")
    suspend fun get_filter(date: String) : List<FitnessTable>

    @Query("Select * From FitnessTable")
    suspend fun get_all() : List<FitnessTable>


}