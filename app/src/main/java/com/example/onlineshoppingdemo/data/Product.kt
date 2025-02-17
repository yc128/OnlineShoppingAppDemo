package com.example.onlineshoppingdemo.data

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageResId: Int? = null
)
