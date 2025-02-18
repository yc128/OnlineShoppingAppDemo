package com.example.onlineshoppingdemo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


/**
 * ViewModel for managing the details of a product in the shopping application.
 *
 *
 * **Key responsibilities:**
 * - Load product details from the database using the product ID.
 * - Expose the product data to the UI in a StateFlow, which ensures the UI automatically
 *   updates when the product details change.
 * - Act as a mediator between the UI and the repository, keeping the UI logic simple and
 *   decoupled from the data access logic.
 *
 * **ViewModel and Repository interaction:**
 * - The ViewModel uses a `ProductRepository` to interact with the database and fetch product data.
 * - The product details are stored in a private `_product` mutable `StateFlow`, which is then exposed
 *   as a read-only `StateFlow` (`product`) to the UI.
 */
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
