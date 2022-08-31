package com.mypc.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mypc.jetweatherforecast.model.Favorite
import com.mypc.jetweatherforecast.model.Unit
import com.mypc.jetweatherforecast.model.Weather

@Database(entities = [Favorite::class,Unit::class],version = 2, exportSchema = false)
abstract class WeatherDataBase :RoomDatabase() {
    abstract fun weatherDao():WeatherDao


}