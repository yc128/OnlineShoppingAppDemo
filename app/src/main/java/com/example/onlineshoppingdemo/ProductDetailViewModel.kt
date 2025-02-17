package com.example.onlineshoppingdemo

import com.example.onlineshoppingdemo.data.ProductDatabase
import com.example.onlineshoppingdemo.data.ProductRepository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshoppingdemo.data.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = ProductDatabase.getDatabase(application).productDao()
    private val repository = ProductRepository(productDao)

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    fun loadProduct(productId: Int) {
        viewModelScope.launch {
            repository.products
                .map { productList -> productList.find { it.id == productId } }
                .collect { entity ->
                    _product.value = entity?.let { Product(it.id, it.name, it.price, it.description, it.imageResId) }
                }
        }
    }
}
