package com.iamnaran.firefly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iamnaran.firefly.data.local.dao.ProductDao
import com.iamnaran.firefly.data.local.dao.UserDao
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, ProductEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao
}