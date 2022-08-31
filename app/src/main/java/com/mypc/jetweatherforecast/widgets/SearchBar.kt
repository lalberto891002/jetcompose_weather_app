package com.mypc.jetweatherforecast.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mypc.jetweatherforecast.components.CommonTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier,
              onSearch:(String) ->Unit
){
    val searchQueryState  = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty()
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder  = "Seattle",
            onAction = KeyboardActions {
                if(!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            }
        )

    }

}


