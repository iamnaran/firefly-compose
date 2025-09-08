package com.iamnaran.firefly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iamnaran.firefly.data.local.dao.ProductDao
import com.iamnaran.firefly.data.local.dao.RecipeDao
import com.iamnaran.firefly.data.local.dao.UserDao
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.local.entities.RecipeEntity
import com.iamnaran.firefly.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, ProductEntity::class, RecipeEntity::class], version = 7, exportSchema = false)
@TypeConverters(AppTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

    abstract fun recipeDao(): RecipeDao


}