package com.example.onlineshoppingdemo


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import com.example.onlineshoppingdemo.data.*


class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = ProductDatabase.getDatabase(application).productDao()
    private val repository = ProductRepository(productDao)

    val products: StateFlow<List<Product>> = repository.products
        .map { entities -> entities.map { Product(it.id, it.name, it.price, it.description) } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // insert several products into the database for display
    init {
        viewModelScope.launch {
            if (repository.products.stateIn(viewModelScope).value.isEmpty()) {
                repository.insertProducts(
                    listOf(
                        ProductEntity(name = "Nike Air Max", price = 150.0, description = "Description for Nike Air Max"),
                        ProductEntity(name = "Adidas Ultraboost", price = 180.0, description = "Description for Adidas Ultraboost"),
                        ProductEntity(name = "Puma RS-X", price = 120.0, description = "Description for Puma RS-X")
                    )
                )
            }
        }
    }
}
