package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.domain.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>

    @GET("products")
    suspend fun getAllProductsByPageSize(@Query("limit") page: Int): Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: String): Response<ProductEntity>


}