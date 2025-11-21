package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ActivityDao {
    @Insert
    suspend fun insert(activity: ActivityEntity)

    @Query("SELECT * FROM activities")
     fun getAll(): LiveData<List<ActivityEntity>>

    @Query("SELECT * FROM activities WHERE date = :selectedDate")
     fun getByDate(selectedDate: String): LiveData<List<ActivityEntity>>

    @Query("SELECT SUM(duration) FROM activities WHERE date = :selectedDate")
     fun getTotalDurationByDate(selectedDate: String): LiveData<Int>
}