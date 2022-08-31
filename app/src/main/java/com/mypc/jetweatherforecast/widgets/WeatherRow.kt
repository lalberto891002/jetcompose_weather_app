package com.mypc.jetweatherforecast.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.utils.Constants
import com.mypc.jetweatherforecast.utils.formatDecimals
import com.mypc.jetweatherforecast.utils.getDayOfWeek
import com.mypc.jetweatherforecast.widgets.WeatherStateImage


@Composable
fun WeatherRow(
        modifier: Modifier = Modifier
            .padding(2.dp)
            .height(70.dp),
        data:WeatherItem,
        onItemClicked:()->Unit = {},
        elevation: Dp = 5.dp,
        backgroundColor:Color = MaterialTheme.colors.background,

) {
    val imageUrl = Constants.BASE_URL_IMAGE +  "${data!!.weather[0].icon}.png"
    Card(
        modifier = modifier.clickable {
            onItemClicked.invoke()
        },
        elevation = elevation,
        backgroundColor = backgroundColor,
       // shape = RoundedCornerShape(20.dp,6.dp,20.dp,20.dp),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp))
        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround) {

            Text(
                text = getDayOfWeek(data.dt),
                style = TextStyle(fontSize = 25.sp,
                fontWeight = FontWeight.Light),
           )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFFC400)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = data.weather[0].description)
            }

            Row{
                Column(
                    Modifier
                        .padding(1.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(
                       // text = buildAnnotatedString {  }
                        text = formatDecimals(data.temp.max) + "°",
                        style = TextStyle(
                            Color(0xFF4180EC),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ))
                }

                Column(
                    Modifier
                        .padding(1.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)  {
                    Text(
                        text = formatDecimals(data.temp.min) + "°",
                        style = TextStyle(
                            Color(0xFFA9ABAD),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ))
                }
            }


        }

    }

}