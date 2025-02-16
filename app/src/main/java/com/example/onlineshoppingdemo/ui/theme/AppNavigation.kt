package com.example.onlineshoppingdemo.ui.theme


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


/**
 * The Layout setting for navigation.
 */
@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = "productList", modifier = modifier) {
        // productList page
        composable("productList") {
            MainScreen(navController = navController)
        }

        // product detail page
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            ProductDetailScreen(productId = productId, navController = navController)
        }
    }
}
