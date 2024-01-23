package com.iamnaran.firefly.data.remote.service

import com.iamnaran.firefly.domain.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getRemoteAllProducts(): Response<ProductResponse>

}