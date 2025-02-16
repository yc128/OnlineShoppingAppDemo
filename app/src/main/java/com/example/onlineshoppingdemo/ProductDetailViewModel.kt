package com.example.onlineshoppingdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductDetailViewModel : ViewModel() {
    private val repository = ProductRepository()
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun loadProduct(productId: Int) {
        _product.value = repository.getProducts().find { product -> product.id == productId }
    }
}