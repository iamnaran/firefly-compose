package com.iamnaran.firefly.domain.usecase.product

import com.iamnaran.firefly.data.repository.product.ProductRepository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke() =
        productRepository.getProducts()


}