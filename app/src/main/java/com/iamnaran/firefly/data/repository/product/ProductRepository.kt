package com.iamnaran.firefly.data.repository.product

import androidx.paging.Pager
import androidx.paging.PagingData
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<Resource<List<ProductEntity>>>
    suspend fun getProductsById(productId: String): Flow<Resource<ProductEntity>>


    fun getPaginatedProducts(): Flow<PagingData<ProductEntity>>



}