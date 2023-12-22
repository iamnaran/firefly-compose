package com.iamnaran.firefly.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamnaran.firefly.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

  abstract fun userDao(): UserDao
}