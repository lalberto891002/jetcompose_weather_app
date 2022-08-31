package com.mypc.jetweatherforecast.widgets

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

@Composable
fun HumidityWindPressureRow(weather: WeatherItem, isImperial: Boolean) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id  = R.drawable.humidity),
                contentDescription = "humidity icon" ,
            modifier = Modifier.size(20.dp))
            Text(text = "${weather.humidity}%",
            style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id  = R.drawable.pressure),
                contentDescription = "pressure icon" ,
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.pressure} pa",
                style = MaterialTheme.typography.caption)
        }

        Row() {
            Icon(painter = painterResource(id  = R.drawable.wind),
                contentDescription = "pressure icon" ,
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.speed}" + if (isImperial) "mph" else "m/s",
                style = MaterialTheme.typography.caption)
        }
    }


}
