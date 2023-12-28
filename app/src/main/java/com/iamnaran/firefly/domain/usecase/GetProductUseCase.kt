package com.iamnaran.firefly.domain.usecase

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.repository.product.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke() =
        productRepository.getProducts()


}