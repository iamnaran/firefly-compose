package com.iamnaran.firefly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iamnaran.firefly.data.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductById(id: Int): Flow<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(superheroes: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("DELETE FROM product")
    suspend fun deleteAllSProduct()

}