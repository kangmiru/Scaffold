package com.e.scaffold.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.scaffold.model.SetoranSampah

@Database(entities = [SetoranSampah::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun setoranSampahDao(): SetoranSampahDao
}
