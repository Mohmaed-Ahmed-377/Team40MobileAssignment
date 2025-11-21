package com.example.myapplication.data

import androidx.lifecycle.LiveData

class ActivityRepository(private val dao: ActivityDao) {
     val allActivities: LiveData<List<ActivityEntity>> = dao.getAll()

     fun getByDate(date: String): LiveData<List<ActivityEntity>> = dao.getByDate(date)

     fun getTotalDuration(date: String): LiveData<Int> = dao.getTotalDurationByDate(date)

    suspend fun insert(activity: ActivityEntity) = dao.insert(activity)
}
