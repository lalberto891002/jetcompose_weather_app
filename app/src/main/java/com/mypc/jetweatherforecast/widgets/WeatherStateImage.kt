package com.mypc.jetweatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun WeatherStateImage(imageUrl: String) {
   Image(
       painter = rememberImagePainter(imageUrl),
       contentDescription = "icon image",
       modifier = Modifier.size(50.dp)
   )
}