package com.mypc.jetweatherforecast.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mypc.jetweatherforecast.model.Unit
import com.mypc.jetweatherforecast.widgets.WeatherAppbar
import dagger.hilt.android.lifecycle.HiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherSettingScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            WeatherAppbar(
                title = "Settings",
                isMainScreen = false,
                elevation = 4.dp,
                icon = Icons.Default.ArrowBack,
                onButtonClicked = { navController.popBackStack() })
        }){


        val settings = settingsViewModel.settingsList.collectAsState().value
        val measurementsUnits = listOf("Imperial(F)", "Metric(C)")
        val defaultCHoice = if(settings.isNullOrEmpty()) measurementsUnits[0]
        else settings.get(0).unit

        val choiceState = remember {
            mutableStateOf(defaultCHoice)

        }

        val unitToogle = defaultCHoice == "Imperial(F)"

        val unitToggleState  = remember{
            mutableStateOf(unitToogle)
        }


        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)) {
            Text(text = "Change Units Meassurements", modifier = Modifier.padding(15.dp))
            
            IconToggleButton(checked = !unitToggleState.value, onCheckedChange = {
                unitToggleState.value= !it
                if(unitToggleState.value){
                    choiceState.value = "Imperial(F)"
                }else{
                    choiceState.value = "Metric(C)"
                }
            }, modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .clip(shape = RectangleShape)
                .padding(5.dp)
                .background(color = Color.Magenta.copy(alpha = 0.4f))) {
                Text(text = if(unitToggleState.value){ "Farenheit °F" }else{ "Celcius °C" })
            }

            Button(onClick = {
                             settingsViewModel.deleteAllUnits()
                settingsViewModel.insertUnit(unit = Unit(choiceState.value))
            },
            modifier = Modifier
                .padding(3.dp)
                .align(CenterHorizontally),
                shape = RoundedCornerShape(34.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEFBE42))) {
                Text(text = "Save",
                    modifier =Modifier.padding(4.dp),
                color = Color.White,
                fontSize = 17.sp)
            }

        }
    }

}