package com.iamnaran.firefly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamnaran.firefly.data.local.dao.ProductDao
import com.iamnaran.firefly.data.local.dao.UserDao
import com.iamnaran.firefly.data.local.entities.Product
import com.iamnaran.firefly.data.local.entities.User

@Database(entities = [User::class, Product::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao
}