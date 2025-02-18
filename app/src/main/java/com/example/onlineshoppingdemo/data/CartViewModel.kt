package com.example.onlineshoppingdemo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the cart in the shopping application.
 *
 * **ViewModel and Repository interaction:**
 * - The ViewModel uses a `ProductRepository` to interact with the database and fetch product data.
 * - The product details are stored in a private `_product` mutable `StateFlow`, which is then exposed
 * as a read-only `StateFlow` (`product`) to the UI.
 *
 * **Key responsibilities:**
 * - Manage cart items (add, remove, update quantity).
 * - Calculate and expose the total price of the cart.
 * - Ensure the cart's state survives configuration changes (e.g., screen rotation).
 */
class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val cartDao = ProductDatabase.getDatabase(application).cartDao()
    private val repository = CartRepository(cartDao)

    val cartItems: StateFlow<List<CartEntity>> = repository.cartItems
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.Lazily, emptyList())

    val totalPrice: StateFlow<Double> = flow {
        emit(repository.getTotalPrice())
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun addToCart(product: Product) {
        viewModelScope.launch { repository.addToCart(product) }
    }

    fun increaseQuantity(productId: Int) {
        viewModelScope.launch { repository.increaseQuantity(productId) }
    }

    fun removeFromCart(productId: Int) {
        viewModelScope.launch { repository.removeFromCart(productId) }
    }

    fun clearCart() {
        viewModelScope.launch { repository.clearCart() }
    }



}
