package com.example.onlineshoppingdemo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.onlineshoppingdemo.data.ProductRepository
import com.example.onlineshoppingdemo.ui.theme.Green800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(navController: NavController, totalPrice: Double) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Green800
                ),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Green800,
                        shape = CircleShape
                    )
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    tint = Color.White,
                    contentDescription = "iconDone"
                )
            }

            Text(
                text = "Order Successful!",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Thank you for your purchase!",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Total Price: $${"%.2f".format(totalPrice)}",  // 格式化显示两位小数
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("productList")},
                colors = ButtonDefaults.buttonColors(containerColor = Green800),
                ) {
                Text(text = "Back to Product list",
                    color = Color.Black)
            }
        }




    }

}


