package com.example.onlineshoppingdemo



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.onlineshoppingdemo.data.CartEntity
import com.example.onlineshoppingdemo.data.CartViewModel
import com.example.onlineshoppingdemo.data.CartViewModelFactory


/**
 * The Layout setting for cart screen.
 * In charge of listen changing data in cart, and update ui.
 * Cart operation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = viewModel(factory = CartViewModelFactory(LocalContext.current.applicationContext as Application))
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Shopping Cart",
                                color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.clearCart() }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Clear Cart")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (cartItems.isEmpty()) {
                Text(
                    text = "Your cart is empty",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cartItems) { item ->
                        CartItemRow(item, viewModel)
                    }


                }

                Text(
                    text = "Total: \$${"%.2f".format(totalPrice)}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End)

                )

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                    onClick = {
                        onCheckout(
                            cartItems = cartItems,
                            navController = navController,
                            viewModel = viewModel,
                            totalPrice = totalPrice)
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text(
                        text = "Proceed to Checkout",
                        color = Color.White
                        )
                }
            }
        }
    }
}


/**
 * Display cart item row.
 */
@Composable
fun CartItemRow(cartItem: CartEntity, viewModel: CartViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = cartItem.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Price: $${cartItem.price} x ${cartItem.quantity}", style = MaterialTheme.typography.bodyLarge)
            }
            IconButton(onClick = { viewModel.removeFromCart(cartItem.productId) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove Item")
            }
        }
    }
}


fun onCheckout(cartItems: List<CartEntity>, navController: NavController, viewModel: CartViewModel, totalPrice: Double) {


    println("Checkout: ${cartItems.size} items, Total: $${totalPrice}")


    viewModel.clearCart()


    navController.navigate("orderConfirmation/${totalPrice}")
}
