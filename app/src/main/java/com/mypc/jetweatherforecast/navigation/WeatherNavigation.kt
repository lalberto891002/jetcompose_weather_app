package com.mypc.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mypc.jetweatherforecast.screens.MainScreen
import com.mypc.jetweatherforecast.screens.WeatherSplashScreen
import com.mypc.jetweatherforecast.screens.about.WeatherAboutScreen
import com.mypc.jetweatherforecast.screens.favourite.WeatherFavoriteScreen
import com.mypc.jetweatherforecast.screens.main.MainViewModel
import com.mypc.jetweatherforecast.screens.search.WeatherSearchScreen
import com.mypc.jetweatherforecast.screens.settings.WeatherSettingScreen

@Composable
fun  WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
        arguments = listOf(
            navArgument(name = "city"){
                type = NavType.StringType
            }
        )){ navBack->
            var myCity = "Bacerlona"
            navBack.arguments?.let {
                myCity =  navBack.arguments!!.get("city").toString()
            }

            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController,mainViewModel,cityName = myCity)
        }
        composable(WeatherScreens.SearchScreen.name){
            WeatherSearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name){
            WeatherAboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name){
            WeatherFavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingScreen.name){
            WeatherSettingScreen(navController = navController)
        }
    }
}







