package com.mypc.jetweatherforecast.repository

import androidx.room.*
import com.mypc.jetweatherforecast.data.WeatherDao
import com.mypc.jetweatherforecast.model.Favorite
import com.mypc.jetweatherforecast.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun getFavbyID(city:String):Favorite = weatherDao.getFavbyID(city)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)


    //settings

    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit:Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit:Unit) = weatherDao.updateUnit(unit)
    suspend fun getUnitById(unit:String) = weatherDao.getUnitById(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit:Unit) = weatherDao.deleteUnit(unit)
    

}





