package com.example.onlineshoppingdemo.data

import com.example.onlineshoppingdemo.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    val products: Flow<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }
}
