package com.example.onlineshoppingdemo.ui.theme


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp


import com.example.onlineshoppingdemo.data.ProductViewModel


/**
 * The Layout setting for product list screen.(Currently set as main screen)
 * @param viewModel The ViewModel for managing product data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        //Add top bar to main screen
        topBar = {
            TopAppBar(
                title = { Text("Product List", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.shadow(12.dp)
            )
        },
        floatingActionButton = { BtnCart(navController)}
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(products) { product ->
                ProductItem(product) {
                    // navi to detail page
                    navController.navigate("productDetail/${product.id}")
                }
            }
        }
    }
}



