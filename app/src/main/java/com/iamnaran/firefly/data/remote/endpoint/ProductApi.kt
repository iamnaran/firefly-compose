package com.iamnaran.firefly.data.remote.endpoint

import com.iamnaran.firefly.domain.dto.AllProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getRemoteAllProducts(): Response<AllProduct>

}