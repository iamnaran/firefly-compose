package com.iamnaran.firefly.data.repository.product

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.remote.BaseApiResponse
import com.iamnaran.firefly.data.remote.pagination.ProductRemoteMediator
import com.iamnaran.firefly.data.remote.service.ProductApi
import com.iamnaran.firefly.di.IoCoroutineScope
import com.iamnaran.firefly.utils.helper.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val appDatabase: AppDatabase,
    @param:IoCoroutineScope private val externalScope: CoroutineScope,
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

    override fun getPaginatedProducts(): Flow<PagingData<ProductEntity>> {

        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = ProductRemoteMediator(productApi, appDatabase)
        ) {
            productDao.getPaginatedProducts()
        }.flow
    }


}