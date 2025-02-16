package com.example.onlineshoppingdemo.ui.theme


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController


import com.example.onlineshoppingdemo.Product
import com.example.onlineshoppingdemo.ProductViewModel


/**
 * The Layout setting for product list screen.(Currently set as main screen)
 * @param viewModel The ViewModel for managing product data.
 */
@Composable
fun MainScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    LazyColumn {
        items(products) { product ->
            ProductItem(product){
                // Navigate to product detail screen when a product is clicked by passing URL
                navController.navigate("productDetail/${product.id}")
            }
        }
    }
}



