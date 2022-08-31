package com.mypc.jetweatherforecast.widgets

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mypc.jetweatherforecast.model.Favorite
import com.mypc.jetweatherforecast.navigation.WeatherScreens
import com.mypc.jetweatherforecast.screens.favourite.FavoriteViewModel
import kotlinx.coroutines.NonCancellable.join
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember as remember

@Preview
@Composable
fun WeatherAppbar(
    title:String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController? = null,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked:() -> Unit = {},
    onButtonClicked: ()->Unit = {}
){

    val showDialog = remember {
        mutableStateOf(false)
    }

  val context = LocalContext.current



    val isInFavorite = favoriteViewModel.favList.collectAsState().value.filter {item->
        item.city.equals(title.split(",")[0])
    }

    if(showDialog.value){

        ShowSettingsDropDownMenu(showDialog = showDialog,navController)

    }



    TopAppBar(
        title = {
                Text(text = title,
                    color = MaterialTheme.colors.onSecondary,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)

                )
        },
        actions = {
            if(isMainScreen){
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "search icon")
                      }


                  }
            IconButton(onClick = {
              showDialog.value = !showDialog.value
            }) {
                Icon(imageVector = Icons.Default.MoreVert,
                    contentDescription = "more icon")
            }
        },
        navigationIcon = {
            if(icon != null){
                    Icon(icon,
                        contentDescription = "icon navigation",
                        tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })

            }

            if(isMainScreen){
                     if(!isInFavorite.isNullOrEmpty()){
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite Icon" ,
                            modifier = Modifier
                                .scale(0.9f)
                                .clickable {
                                    val title_array = title.split(",")
                                    favoriteViewModel.deleteFavorite(
                                        Favorite(
                                            city = title_array[0].trim(),
                                            country = title_array[1].trim()
                                        )
                                    )

                                },
                            tint = Color.Red.copy(alpha = 0.9f))

                    }else{
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favorite Icon" ,
                            modifier = Modifier
                                .scale(0.9f)
                                .clickable {
                                    val title_array = title.split(",")
                                    favoriteViewModel.insertFavorite(
                                        Favorite(
                                            city = title_array[0].trim(),
                                            country = title_array[1].trim()
                                        )
                                    )
                                    Toast
                                        .makeText(context, "${title_array[0].trim()}  added" +
                                                " to" +
                                                " favorite", Toast.LENGTH_LONG)
                                        .show()

                                },
                            tint = Color.Red.copy(alpha = 0.9f))

                    }


            }

        },
        backgroundColor = Color.Transparent,
        elevation = elevation

    )

}

@Composable
fun ShowSettingsDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController?) {

    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("About","Favorites","Settings")
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        DropdownMenu(expanded = expanded ,
            onDismissRequest = { expanded = false
                showDialog.value = !showDialog.value },
        modifier = Modifier
            .width(140.dp)
            .background(Color.White)) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = { 
                    expanded = false
                    }) {
                    Icon(imageVector = when (text){
                        "About" ->Icons.Default.Info
                        "Favorites" -> Icons.Default.FavoriteBorder
                         else -> Icons.Default.Settings
                                             } , contentDescription = null,
                    tint = Color.LightGray)
                    Text(text = text,
                        modifier = Modifier.clickable {
                                                      navController?.navigate(
                                                          when(text){
                                                              "About" ->WeatherScreens.AboutScreen.name
                                                              "Favorites" -> WeatherScreens.FavoriteScreen.name
                                                              else -> WeatherScreens.SettingScreen.name
                                                          }

                                                      )
                        },
                        fontWeight = FontWeight.W300)
                }

            }
            
        }

    }
}
