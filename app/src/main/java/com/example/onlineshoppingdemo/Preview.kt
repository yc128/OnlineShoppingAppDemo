package com.example.onlineshoppingdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.onlineshoppingdemo.ui.theme.AppNavigation
import com.example.onlineshoppingdemo.ui.theme.OnlineShoppingDemoTheme
import com.example.onlineshoppingdemo.ui.theme.ProductDetailScreen


@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    val fakeNavController = rememberNavController()

    OnlineShoppingDemoTheme {
        AppNavigation(navController = fakeNavController)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ProductDetailScreen(productId = 1, navController = rememberNavController())
}

