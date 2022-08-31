package com.mypc.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypc.jetweatherforecast.data.DataOrException
import com.mypc.jetweatherforecast.model.Weather
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.model.WeatherObject
import com.mypc.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel() {
    suspend fun getWeatherData(city:String,qtn:Int,unit:String = "Imperial"):DataOrException<Weather,Boolean,Exception>{
        return repository.getWeather(cityQuery = city, quantity = qtn,unit = unit)
    }
}