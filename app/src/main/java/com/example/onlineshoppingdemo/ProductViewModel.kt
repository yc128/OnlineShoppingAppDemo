package com.example.onlineshoppingdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    // Mutable state flow for products
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    // Expose a read-only state flow
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = repository.getProducts()
    }
}
