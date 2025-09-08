package com.iamnaran.firefly.data.remote.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.iamnaran.firefly.data.local.AppDatabase
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.remote.service.ProductApi
import com.iamnaran.firefly.utils.AppLog

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(private val productApi: ProductApi,
                            val appDatabase: AppDatabase) :
    RemoteMediator<Int, ProductEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                lastItem?.id ?: 0
            }
        }

        return try {
            val response = productApi.getAllPaginatedProducts(skip = page.toInt(), limit = state.config.pageSize)
            AppLog.showLog("Called Pagination -> ${response.skip} --> ${response.limit} ---> ${response.productEntities.size}" )
            appDatabase.productDao().insertAllProducts(response.productEntities)
            MediatorResult.Success(endOfPaginationReached = response.productEntities.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }


}