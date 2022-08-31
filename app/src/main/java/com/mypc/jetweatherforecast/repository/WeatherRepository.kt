package com.mypc.jetweatherforecast.repository

import android.util.Log
import com.mypc.jetweatherforecast.data.DataOrException
import com.mypc.jetweatherforecast.model.Weather
import com.mypc.jetweatherforecast.model.WeatherItem
import com.mypc.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api:WeatherApi)  {
    suspend fun getWeather(cityQuery:String,quantity:Int,unit:String):DataOrException<Weather,Boolean,Exception>{
        val response = try {
            api.getWeather(query = cityQuery, cnt = quantity, units = unit )
        }catch (e:Exception){
            Log.d("Error:",e.message!!)
            return DataOrException(e = e)

        }
        Log.d("INSIDE:","received:${response}")
    return DataOrException(data = response)
    }
}