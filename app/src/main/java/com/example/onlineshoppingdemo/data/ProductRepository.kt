package com.example.onlineshoppingdemo.data

import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    // read all products from the database
    val products: Flow<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }
}
