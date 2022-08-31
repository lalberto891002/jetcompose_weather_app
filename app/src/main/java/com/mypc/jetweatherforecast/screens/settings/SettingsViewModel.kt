package com.mypc.jetweatherforecast.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypc.jetweatherforecast.model.Favorite
import com.mypc.jetweatherforecast.model.Unit
import com.mypc.jetweatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository) :ViewModel(){

    private val _settingsList = MutableStateFlow<List<Unit>>(emptyList())
    val settingsList = _settingsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getUnits()
                .distinctUntilChanged()
                .collect{listOfUnits ->
                if(listOfUnits.isNullOrEmpty()){
                    Log.d("Tag","empty settings lists")
                }else{
                    _settingsList.value = listOfUnits
                    Log.d("Tag"," settings lists: ${_settingsList.value}")
                }

            }
        }
    }




    fun insertUnit(unit: Unit) =
        viewModelScope.launch {
            Log.d("Tag","Saving unitL${unit.unit}")
            repository.insertUnit(unit)
        }



    fun updateUnit(unit:Unit) =
        viewModelScope.launch {
            repository.updateUnit(unit)
        }

    fun deleteUnit(unit:Unit) =
        viewModelScope.launch {
            repository.deleteUnit(unit)
        }

    fun deleteAllUnits() =
        viewModelScope.launch {
            repository.deleteAllUnits()
        }

    fun getUnitById(unit:String) =
        viewModelScope.launch {
            repository.getUnitById(unit)
        }
}