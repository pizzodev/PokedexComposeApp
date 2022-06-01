package com.example.pokedexapp.presentation.screens.pokemonDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun PokemonDetailScreen(navController: NavController, _name: String?) {

    val vm = hiltViewModel<PokemonDetailViewModel>()
    val pokemonDetailState = vm.pokemonDetailRefresh.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
       Text(text = pokemonDetailState?.name?:"Unknown")
    }

    vm.initViewModel(_name?:"")

}