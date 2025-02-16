package com.example.onlineshoppingdemo

class ProductRepository {
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Nike Air Max", 150.0),
            Product(2, "Adidas Ultraboost", 180.0),
            Product(3, "Puma RS-X", 120.0)
        )
    }
}
