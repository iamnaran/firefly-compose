package com.iamnaran.firefly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.firefly.data.local.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getSuperheroByName(id: Int): Flow<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(superheroes: List<Product>)

    @Query("DELETE FROM product")
    suspend fun deleteAllSuperheroes()

}