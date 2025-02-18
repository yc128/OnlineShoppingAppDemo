package com.example.onlineshoppingdemo.data


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CartRepository(private val cartDao: CartDao) {
    val cartItems: Flow<List<CartEntity>> = cartDao.getCartItems()

    suspend fun addToCart(product: Product) {
        val existingItem = cartDao.getCartItemById(product.id)
        if(existingItem != null) {
            cartDao.increaseQuantity(product.id)
        }else{
            cartDao.insertCartItem(CartEntity(productId = product.id, name = product.name, price = product.price, quantity = 1))
        }

    }

    suspend fun increaseQuantity(productId: Int) {
        cartDao.increaseQuantity(productId)
    }

    suspend fun removeFromCart(productId: Int) {
        cartDao.removeCartItem(productId)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }


    suspend fun getTotalPrice(): Double {
        val items = cartDao.getCartItems().first() // find all
        return items.sumOf { it.price * it.quantity } // 计算总价
    }
}
