package com.example.onlineshoppingdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.onlineshoppingdemo.ui.theme.AppNavigation
import com.example.onlineshoppingdemo.ui.theme.OnlineShoppingDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineShoppingDemoTheme {
                val navController = rememberNavController() // create navController

                Scaffold { paddingValues ->
                    AppNavigation(navController, modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}


