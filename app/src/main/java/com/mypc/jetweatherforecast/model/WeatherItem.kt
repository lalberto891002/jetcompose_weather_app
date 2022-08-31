package com.mypc.jetweatherforecast.model

data class WeatherItem(
    val clouds: Double,
    val deg: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<WeatherObject>
)