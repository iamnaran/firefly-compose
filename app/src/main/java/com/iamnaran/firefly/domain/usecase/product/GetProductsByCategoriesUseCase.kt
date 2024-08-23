package com.iamnaran.firefly.domain.usecase.product

import com.iamnaran.firefly.data.local.entities.ProductEntity
import com.iamnaran.firefly.data.repository.product.ProductRepository
import com.iamnaran.firefly.domain.dto.CategoryWithProducts
import javax.inject.Inject

class GetProductsByCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository

) {

    suspend operator fun invoke(products: List<ProductEntity>): List<CategoryWithProducts> {
        val groupedProducts = products.groupBy { it.category }

        return groupedProducts.map { (category, products) ->
            CategoryWithProducts(
                categoryName = category,
                products = products.map { productEntity ->
                    ProductEntity(
                        id = productEntity.id,
                        title = productEntity.title,
                        description = productEntity.description,
                        price = productEntity.price,
                        rating = productEntity.rating,
                        stock = productEntity.stock,
                        thumbnail = productEntity.thumbnail,
                        category = productEntity.category
                    )
                }
            )
        }
    }
}