package com.mypc.jetweatherforecast.network

import com.mypc.jetweatherforecast.model.Weather
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.model.WeatherObject
import com.mypc.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units:String = "imperial",
        @Query("appid") appid:String = Constants.API_KEY,
        @Query("cnt") cnt:Int = 1
    ): Weather
}