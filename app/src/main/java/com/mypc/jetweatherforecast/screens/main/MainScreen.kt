package com.mypc.jetweatherforecast.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mypc.jetweatherforecast.components.WeatherRow
import com.mypc.jetweatherforecast.data.DataOrException
import com.mypc.jetweatherforecast.model.Weather
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.navigation.WeatherScreens
import com.mypc.jetweatherforecast.screens.main.MainViewModel
import com.mypc.jetweatherforecast.screens.settings.SettingsViewModel
import com.mypc.jetweatherforecast.utils.Constants
import com.mypc.jetweatherforecast.utils.formatDate
import com.mypc.jetweatherforecast.utils.formatDecimals
import com.mypc.jetweatherforecast.widgets.HumidityWindPressureRow
import com.mypc.jetweatherforecast.widgets.SunriseSunset
import com.mypc.jetweatherforecast.widgets.WeatherAppbar
import com.mypc.jetweatherforecast.widgets.WeatherStateImage


@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    cityName: String,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    val unitFromDb = settingsViewModel.settingsList.collectAsState().value
    var unit by remember {
        mutableStateOf("Imperial")
    }

    var isImperial by remember {
        mutableStateOf(false)
    }


    if(!unitFromDb.isNullOrEmpty()){
        unit = unitFromDb[0].unit.split("(")[0].lowercase()
        isImperial = unit == "imperial"

        val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
            initialValue = DataOrException(loading = true)){
            value = mainViewModel.getWeatherData(city = cityName, qtn = 15,unit = unit)
        }.value
        if(weatherData.loading == true){
            CircularProgressIndicator()
        }else if(weatherData.data != null) {

            MainScaffold(weather = weatherData.data!!,navController,isImperial = isImperial)
            weatherData.loading = false
        }else{

            MainScaffold(null, navController, isImperial)
            weatherData.loading = false

        }

    }




}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather?, navController: NavController, isImperial: Boolean) {

    var titleCity = ""
    weather?.city?.let {
        titleCity = it.name + "," + it.country}
    Scaffold(topBar = {
        WeatherAppbar(title = titleCity,
            navController = navController,
            elevation = 5.dp,
        onButtonClicked = {
            Log.d("TestClick","Back Clicked")

        },
        onAddActionClicked = {
            navController.navigate(WeatherScreens.SearchScreen.name)
        })
    }) {
        weather?.let {
            Maincontent(data = it,isImperial = isImperial)
        }
        if(weather == null){
            Text(text = "Upps it seems this city does not exist!!")
        }


    }

    

}

@Composable
fun Maincontent(data: Weather, isImperial: Boolean) {
    val weatherItem = data.list[0]
    val todayDate = formatDate(weatherItem.dt)

    val date = remember {
        mutableStateOf(todayDate)
    }

    val temperature = remember {
        mutableStateOf(formatDecimals(weatherItem.temp.day))
    }

    val comment = remember {
        mutableStateOf(weatherItem.weather[0].description)
    }

    val img = remember {
        mutableStateOf(Constants.BASE_URL_IMAGE + "${weatherItem.weather[0].icon}.png")
    }

    val weatherItemState = remember {
        mutableStateOf(weatherItem)
    }



    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = date.value,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(160.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                WeatherStateImage(imageUrl = img.value)
                Text(text = temperature.value + "°" , style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = comment.value, fontStyle = FontStyle.Italic)

                }
            }

        HumidityWindPressureRow(weather = weatherItemState.value,isImperial = isImperial)
        Divider()
        SunriseSunset(weather =  weatherItemState.value)
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
            Text(
                text = "This Week",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        DayliWeather(weather = data) { item ->
            date.value = formatDate(item.dt)
            temperature.value = formatDecimals(item.temp.day)
            comment.value = item.weather[0].description
            img.value = Constants.BASE_URL_IMAGE + "${item.weather[0].icon}.png"
            weatherItemState.value = item
        }
    }



}

@Composable
fun DayliWeather(weather: Weather,ClickItem:(WeatherItem)->Unit) {
    val dayList = weather.list
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)){
        items(dayList){ day ->
            WeatherRow(data = day, onItemClicked = {
                Log.d("Clicked:", formatDate(day.dt))
                ClickItem.invoke(day)
            })

        }
    }
}









