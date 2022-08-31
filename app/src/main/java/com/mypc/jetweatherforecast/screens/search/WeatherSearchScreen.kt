package com.mypc.jetweatherforecast.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mypc.jetweatherforecast.navigation.WeatherScreens
import com.mypc.jetweatherforecast.widgets.SearchBar
import com.mypc.jetweatherforecast.widgets.WeatherAppbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherSearchScreen(navController: NavController){
    Scaffold(topBar = {
        WeatherAppbar(
            title = "Search",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
        ){navController.popBackStack()}
    }) {
        Surface() {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                    ){ searchCity ->
                    Log.d("search for:",searchCity)
                    navController.navigate(WeatherScreens.MainScreen.name+"/$searchCity")

                }
            }
        }
    }
}

