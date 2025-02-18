package com.example.onlineshoppingdemo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
