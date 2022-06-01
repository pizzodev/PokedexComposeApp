package com.example.pokedexapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.example.pokedexapp.presentation.navigation.PokedexScreens
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

        PokemonList(pokemonListState) { name ->
            navController.navigate(PokedexScreens.PokemonDetailScreen.name + "/$name")
        }
    }
}

@Composable
fun PokemonList(pokemonList: List<Pokemon>, onItemClickCbk: (name: String) -> Unit) {
    LazyColumn {
        items(items = pokemonList) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        onItemClickCbk.invoke(it.name)
                    },
                text = "Id: ${it.id} - Name: ${it.name}"
            )
        }
    }
}

@Composable
private fun ReloadPokemon(cbk: () -> Unit) {
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
private fun EraseDatabase(cbk: () -> Unit) {
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