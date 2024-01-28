package com.iamnaran.firefly.data.repository.product

import androidx.room.withTransaction
import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.remote.BaseApiResponse
import com.iamnaran.firefly.data.remote.service.ProductApi
import com.iamnaran.firefly.di.qualifiers.DefaultDispatcher
import com.iamnaran.firefly.di.qualifiers.IoDispatcher
import com.iamnaran.firefly.utils.helper.networkBoundResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
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
            productApi.getAllProducts()
        },
        saveFetchResult = { products ->
            appDatabase.withTransaction {
                productDao.insertAllProducts(products.body()!!.productEntities)
            }
        }

    )

    override suspend fun getProductsById(productId: String) = networkBoundResource(
        query = {
            productDao.getProductById(productId.toInt())
        },
        fetch = {
            productApi.getProductById(productId)
        },
        saveFetchResult = { product ->
            appDatabase.withTransaction {
                productDao.insertProduct(product.body()!!)
            }
        }

    )

}