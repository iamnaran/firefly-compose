package com.iamnaran.firefly.utils.common

class Product private constructor(
    val id: String,
    val name: String,
    val price: Double
) {
    // Builder class
    data class Builder(
        var id: String = "",
        var name: String = "",
        var price: Double = 0.0
    ) {
        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun price(price: Double) = apply { this.price = price }
        fun build() = Product(id, name, price)
    }

    override fun toString(): String {
        return "Product(id='$id', name='$name', price=$price)"
    }
}

val product1 = Product.Builder()
    .id("001")
    .name("Laptop")
    .price(999.99)
    .build()