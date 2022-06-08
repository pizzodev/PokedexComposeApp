package com.example.pokedexapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.pokedexapp.presentation.navigation.PokedexNavigation
import com.example.pokedexapp.presentation.theme.PokedexAppTheme
import com.example.pokedexapp.presentation.ui.screens.components.PokedexLoader
import com.example.pokedexapp.presentation.utils.LoadingStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val changingStatusState = remember { mutableStateOf(LoadingStatus.IDLE) }

            PokedexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PokedexNavigation(changingStatusState)
                    PokedexLoader(changingStatusState)
                }


            }
        }
    }
}




