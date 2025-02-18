package com.example.onlineshoppingdemo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getCartItems(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartEntity)

    @Query("UPDATE cart SET quantity = quantity + 1 WHERE productId = :productId")
    suspend fun increaseQuantity(productId: Int)

    @Query("DELETE FROM cart WHERE productId = :productId")
    suspend fun removeCartItem(productId: Int)

    @Query("DELETE FROM cart")
    suspend fun clearCart()

    @Query("SELECT * FROM cart WHERE productId = :productId LIMIT 1")
    suspend fun getCartItemById(productId: Int): CartEntity?
}
