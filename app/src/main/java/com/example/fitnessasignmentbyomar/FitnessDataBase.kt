package com.example.fitnessasignmentbyomar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [FitnessTable::class], version = 1)
abstract class FitnessDataBase: RoomDatabase() {
    abstract  fun  fitnessDao(): FitnessDao
    companion object {
        @Volatile
        private var instance: FitnessDataBase? = null

        fun getInstance(context: Context): FitnessDataBase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    FitnessDataBase::class.java,
                    "fitnessdp"
                ).build()
                instance = tempInstance
                tempInstance
            }
        }
    }

}