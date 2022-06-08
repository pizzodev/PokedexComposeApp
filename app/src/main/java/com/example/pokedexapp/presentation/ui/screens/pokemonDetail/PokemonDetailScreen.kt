package com.example.pokedexapp.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokedexapp.presentation.utils.LoadingStatus

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    loadingState: MutableState<LoadingStatus>,
    _name: String?
) {

    val vm = hiltViewModel<PokemonDetailViewModel>()
    val pokemonDetailState = vm.pokemonDetailRefresh.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = pokemonDetailState?.name?:"Unknown")

        Image(
            painter = rememberImagePainter(
                data = pokemonDetailState?.sprites?.front_default,
            ),
            contentDescription = "Pokemon sprite"
        )
    }

    vm.initViewModel(loadingState,_name?:"")

}