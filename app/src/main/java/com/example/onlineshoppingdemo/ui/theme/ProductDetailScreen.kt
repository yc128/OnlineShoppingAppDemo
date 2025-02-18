package com.example.onlineshoppingdemo.ui.theme


import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.onlineshoppingdemo.data.CartViewModel
import com.example.onlineshoppingdemo.data.CartViewModelFactory
import com.example.onlineshoppingdemo.data.ProductDetailViewModel
import com.example.onlineshoppingdemo.data.ProductDetailViewModelFactory
import kotlinx.coroutines.launch

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
    val cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory(context))

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }



    // This can ensure that the product data is loaded only when the productId changes
    LaunchedEffect(productId) {
        productId?.let { viewModel.loadProduct(productId) }
    }

    // Collect the product state and value from the ViewModel
    val productState = viewModel.product.collectAsState()
    val product = productState.value

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
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
        },

        floatingActionButton = { BtnCart(navController)}


    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)

        ) {
            if (product != null) {
                Box(modifier = Modifier.fillMaxSize()){
                    // Draw image
                    ProductImage(imageResId = product.imageResId)



                    // Card view for product info text
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.7f)
                            .align(Alignment.BottomCenter),
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        )
                    ) {
                            Box(modifier = Modifier.fillMaxSize()) {

                                // "Add to Cart" Button
                                FloatingActionButton(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding( 16.dp),
                                    shape = CircleShape,
                                    elevation = FloatingActionButtonDefaults.elevation(
                                        defaultElevation = 12.dp
                                    ),
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    onClick = {
                                        cartViewModel.addToCart(product)
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "Added to Cart!",
                                                duration = SnackbarDuration.Short)
                                        }
                                    }
                                ) {
                                    CartWithAddIcon()

                                }

                                // Other text info
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
                            }
                    }
                }



            } else {
                Text(text = "Product not found", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }


}
