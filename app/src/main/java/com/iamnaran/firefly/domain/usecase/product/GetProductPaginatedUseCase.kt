package com.iamnaran.firefly.domain.usecase.product

import androidx.paging.PagingData
import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductPaginatedUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): Flow<PagingData<ProductEntity>> = productRepository.getPaginatedProducts()


}