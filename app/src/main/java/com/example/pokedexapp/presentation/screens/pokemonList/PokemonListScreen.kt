package com.example.pokedexapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.presentation.screens.pokemonList.PokemonListViewModel

@Composable
fun PokemonListScreen(navController: NavController) {

    val vm = hiltViewModel<PokemonListViewModel>()
    val pokemonListState = vm.pokemonListRefresh.collectAsState().value

    Column() {

        Row() {
            ReloadPokemon { vm.reloadList() }
            EraseDatabase { vm.eraseDatabase() }
        }

        PokemonList(pokemonListState)
    }
}

@Composable
fun PokemonList(pokemonList: List<Pokemon>) {
    LazyColumn {
        items(items = pokemonList) {
            Text(text = "Id: ${it.id} - Name: ${it.name}")
        }
    }
}

@Composable
fun ReloadPokemon(cbk: () -> Unit) {
    Surface(modifier =
    Modifier
        .size(50.dp)
        .clickable {
            cbk.invoke()
        }) {
        Icon(
            Icons.Default.AddCircle,
            contentDescription = "...",
            tint = Color.Black,
        )
    }
}

@Composable
fun EraseDatabase(cbk: () -> Unit) {
    Surface(modifier =
    Modifier
        .size(50.dp)
        .clickable {
            cbk.invoke()
        }) {
        Icon(
            Icons.Default.Clear,
            contentDescription = "...",
            tint = Color.Black,
        )
    }
}