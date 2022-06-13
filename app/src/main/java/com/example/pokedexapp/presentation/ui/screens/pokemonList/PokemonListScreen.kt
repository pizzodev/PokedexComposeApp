package com.example.pokedexapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexapp.data.model.PokemonWithDetail
import com.example.pokedexapp.presentation.navigation.PokedexScreens
import com.example.pokedexapp.presentation.ui.screens.components.PokemonRow
import com.example.pokedexapp.presentation.ui.screens.pokemonList.PokemonListViewModel
import com.example.pokedexapp.presentation.utils.LoadingStatus

@Composable
fun PokemonListScreen(navController: NavController, loadingState: MutableState<LoadingStatus>) {

    val vm = hiltViewModel<PokemonListViewModel>()

    val pokemonListState = remember { mutableStateOf(emptyList<PokemonWithDetail>()) }
    //val showEmptyState = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    ReloadPokemon { vm.reloadList() }
                    EraseDatabase { vm.eraseDatabase() }
                }
            }
        }
    ) {
        if (pokemonListState.value.isEmpty()) {
            PokemonEmptyState()
        } else {
            PokemonList(pokemonListState.value) { name ->
                navController.navigate(PokedexScreens.PokemonDetailScreen.name + "/$name")
            }
        }
    }

    vm.initViewModel(loadingState, pokemonListState)
}

@Composable
fun PokemonList(pokemonList: List<PokemonWithDetail>, onItemClickCbk: (name: String) -> Unit) {
    LazyColumn {
        items(items = pokemonList) {
            PokemonRow(it, onItemClickCbk)
        }
    }
}

@Composable
fun PokemonEmptyState() {
    Text(text = "EMPTY")
}

@Composable
private fun ReloadPokemon(cbk: () -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .clickable {
            cbk.invoke()
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(25.dp)
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "...",
                tint = Color.White,
            )
        }
        Text(text = "Random 20")
    }
}

@Composable
private fun EraseDatabase(cbk: () -> Unit) {
    Column(modifier = Modifier
        .padding(5.dp)
        .clickable {
            cbk.invoke()
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(25.dp)
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "...",
                tint = Color.White,
            )
        }
        Text(text = "Erase stored")
    }
}