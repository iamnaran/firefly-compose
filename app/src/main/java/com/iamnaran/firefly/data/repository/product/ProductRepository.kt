package com.iamnaran.firefly.data.repository.product

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(): Flow<Resource<List<ProductEntity>>>

}