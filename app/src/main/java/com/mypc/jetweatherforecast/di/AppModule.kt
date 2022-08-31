package com.mypc.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.mypc.jetweatherforecast.data.WeatherDao
import com.mypc.jetweatherforecast.data.WeatherDataBase
import com.mypc.jetweatherforecast.model.Weather
import com.mypc.jetweatherforecast.network.WeatherApi
import com.mypc.jetweatherforecast.utils.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDataBase: WeatherDataBase):WeatherDao =
        weatherDataBase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context:Context):WeatherDataBase{

       return  Room.databaseBuilder(context,
            WeatherDataBase::class.java,
            "weather_database")
            .fallbackToDestructiveMigration()
            .build()

    }


    @Singleton
    @Provides
    fun provideOpenWeatherApi():WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }




}