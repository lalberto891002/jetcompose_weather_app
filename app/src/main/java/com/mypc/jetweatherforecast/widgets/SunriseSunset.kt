package com.mypc.jetweatherforecast.widgets

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mypc.jetweatherforecast.R
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.utils.formatHour

@Composable
fun SunriseSunset(weather: WeatherItem) {
  Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

          Row(modifier = Modifier.padding(2.dp)) {
             Icon(painter = painterResource(id = R.drawable.sunrise),
                 contentDescription = "Icon SunRise",
             modifier = Modifier.size(30.dp))
            Text(text = formatHour(weather.sunrise), style = MaterialTheme.typography.caption)
              Log.d("Composite Sunrise",weather.sunrise.toString())
          }


          Row(modifier = Modifier.padding(2.dp)) {
              Icon(painter = painterResource(id = R.drawable.sunset),
                  contentDescription = "Icon Sunset",
                  modifier = Modifier.size(30.dp))
              Text(text = formatHour(weather.sunset), style = MaterialTheme.typography.caption)
              Log.d("Composite Sunset",weather.sunset.toString())
          }
      }
  }
