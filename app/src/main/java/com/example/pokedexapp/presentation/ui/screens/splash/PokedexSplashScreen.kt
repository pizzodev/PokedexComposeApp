package com.example.pokedexapp.presentation.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexapp.R
import com.example.pokedexapp.presentation.navigation.PokedexScreens
import com.example.pokedexapp.presentation.ui.screens.components.PokedexLoader
import com.example.pokedexapp.presentation.utils.LoadingStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PokedexSplashScreen(navController: NavController) {

    val splashStatus = remember { mutableStateOf(value = LoadingStatus.LOADING) }

    LaunchedEffect(key1 = true, block = {

        delay(1500L)

        splashStatus.value = LoadingStatus.COMPLETED

        navController.navigate(PokedexScreens.PokemonListScreen.name)
    } )

    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(Color.White)
    ) {
        PokedexLoader(splashStatus)
    }

}