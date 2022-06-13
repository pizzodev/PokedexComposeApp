package com.example.pokedexapp.presentation.ui.screens.pokemonDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Card(
            Modifier
                .padding(15.dp, 10.dp, 15.dp, 10.dp)
                .wrapContentSize(Alignment.Center)
                .background(Color.White),
            elevation = 5.dp
        ) {
            Column() {
                Image(
                    modifier = Modifier
                        .padding(15.dp, 15.dp, 15.dp, 15.dp)
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(0.5.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Fit,
                    painter = rememberImagePainter(
                        data = pokemonDetailState.value?.sprites?.front_default,
                    ),
                    contentDescription = "Pokemon sprite"
                )

                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    val pokemonName = pokemonDetailState.value?.name?.capitalize(Locale.current).orEmpty()
                    val pokemonHeight = pokemonDetailState.value?.height
                    val pokemonWeight = pokemonDetailState.value?.weight
                    val pokemonEncounterLocations = pokemonDetailState.value?.location_area_encounters
                    val pokemonTypes = pokemonDetailState.value?.types

                    Text(text = "Name: $pokemonName")
                    Text(text = "Height: $pokemonHeight m")
                    Text(text = "Weight: $pokemonWeight kg")
                    Text(text = "Found at: ${pokemonEncounterLocations?.map { it.locationAreaName }}")
                    Text(text = "Types: ${pokemonTypes?.map { it.type.name }}")
                }
            }
        }
    }

    vm.initViewModel(loadingState, _name ?: "")
}