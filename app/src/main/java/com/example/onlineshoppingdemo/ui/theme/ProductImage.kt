package com.example.onlineshoppingdemo.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProductImage(imageResId: Int?) {
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(240.dp)

    imageResId?.let {
        Image(
            painter = painterResource(id = it),
            contentDescription = "Product Image",
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
    }
}
