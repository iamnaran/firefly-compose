package com.iamnaran.firefly.domain.usecase.product

import com.iamnaran.firefly.data.repository.auth.AuthRepository
import com.iamnaran.firefly.data.repository.product.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(productId: String) =
        productRepository.getProductsById(productId)


}