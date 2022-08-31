package com.mypc.jetweatherforecast.screens.favourite

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypc.jetweatherforecast.model.Favorite
import com.mypc.jetweatherforecast.repository.WeatherDbRepository
import com.mypc.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository)
    :ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getFavorites().distinctUntilChanged()
                .collect{listOfFavs ->
                    if(listOfFavs.isNullOrEmpty()){
                        Log.d("TAG","empty favs")
                    }else{
                        _favList.value = listOfFavs
                        Log.d("TAG",":${_favList.value}")
                    }
                }
        }
    }


    fun insertFavorite(favorite:Favorite) =
        viewModelScope.launch {
            Log.d("Tag","saving city:${favorite.city} in country:${favorite.country}")
            repository.insertFavorite(favorite)
        }

    fun updateFavorite(favorite:Favorite) =
        viewModelScope.launch {
            repository.updateFavorite(favorite)
        }

    fun deleteFavorite(favorite:Favorite) =
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }

    fun deleteAllFavorites() =
        viewModelScope.launch {
            repository.deleteAllFavorites()
        }

    fun getFavbyID(city:String) =
        viewModelScope.launch {
            repository.getFavbyID(city)
        }
}

