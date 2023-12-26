package com.iamnaran.firefly.data.api.endpoint

import com.iamnaran.firefly.data.api.Resource
import com.iamnaran.firefly.data.entities.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): Resource<Product>

}