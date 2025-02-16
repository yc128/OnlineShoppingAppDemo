package com.example.onlineshoppingdemo.ui.theme


import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.onlineshoppingdemo.ProductDetailViewModel
import com.example.onlineshoppingdemo.ProductDetailViewModelFactory

/**
 * The Layout setting for product detail screen.
 * @param productId The ID of the product to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int?,
    navController: NavController,
    ) {

    val context = LocalContext.current.applicationContext as Application
    val viewModel: ProductDetailViewModel = viewModel(factory = ProductDetailViewModelFactory(context))



    // This can ensure that the product data is loaded only when the productId changes
    LaunchedEffect(productId) {
        productId?.let { viewModel.loadProduct(productId) }
    }

    // Collect the product state and value from the ViewModel
    val productState = viewModel.product.collectAsState()
    val product = productState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.shadow(12.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (product != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = product.name, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Price: $${product.price}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Description: ${product.description}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                Text(text = "Product not found", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }



}
