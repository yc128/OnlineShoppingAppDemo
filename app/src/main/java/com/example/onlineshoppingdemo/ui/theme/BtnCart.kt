package com.example.onlineshoppingdemo.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BtnCart(navController: NavController){

    FloatingActionButton(
            onClick = { navController.navigate("cart") },
            containerColor = MaterialTheme.colorScheme.secondary,
            shape = CircleShape
    ) {
            Icon(imageVector = Icons.Default.ShoppingCart,
                tint = Color.White,
                contentDescription = "Cart")
    }

}