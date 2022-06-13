package com.example.pokedexapp.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.presentation.utils.LoadingStatus
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    loadingState: MutableState<LoadingStatus>,
    _name: String?
) {

    val vm = hiltViewModel<PokemonDetailViewModel>()

    val pokemonDetailState = vm.pokemonDetailState.asStateFlow().collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = pokemonDetailState.value?.name?:"Unknown")

        Image(
            painter = rememberImagePainter(
                data = pokemonDetailState.value?.sprites?.front_default,
            ),
            contentDescription = "Pokemon sprite"
        )
    }

    vm.initViewModel(loadingState,_name?:"")

}