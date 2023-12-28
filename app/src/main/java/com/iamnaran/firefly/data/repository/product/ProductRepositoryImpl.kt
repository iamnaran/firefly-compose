package com.iamnaran.firefly.data.repository.product

import androidx.room.withTransaction
import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.local.dao.ProductDao
import com.iamnaran.firefly.data.local.entities.Product
import com.iamnaran.firefly.data.remote.BaseApiResponse
import com.iamnaran.firefly.data.remote.endpoint.ProductApi
import com.iamnaran.firefly.di.qualifiers.DefaultDispatcher
import com.iamnaran.firefly.di.qualifiers.IoDispatcher
import com.iamnaran.firefly.utils.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val appDatabase: AppDatabase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val externalScope: CoroutineScope,
) : ProductRepository, BaseApiResponse() {


    private val productDao = appDatabase.productDao()


     override suspend fun getProducts() = networkBoundResource(
        query = {
            productDao.getAllProducts()
        },
        fetch = {
            productApi.getRemoteAllProducts()
        },
        saveFetchResult = { products ->
            appDatabase.withTransaction {
                productDao.insertAllProducts(products.body()!!.products)
            }
        }

    )

}