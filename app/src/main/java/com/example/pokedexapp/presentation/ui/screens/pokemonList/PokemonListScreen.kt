package com.example.pokedexapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokedexapp.R
import com.example.pokedexapp.data.model.PokemonWithDetail
import com.example.pokedexapp.presentation.navigation.PokedexScreens
import com.example.pokedexapp.presentation.ui.screens.components.PokemonRow
import com.example.pokedexapp.presentation.ui.screens.pokemonList.PokemonListViewModel
import com.example.pokedexapp.presentation.utils.LoadingStatus
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PokemonListScreen(navController: NavController, loadingState: MutableState<LoadingStatus>) {

    val vm = hiltViewModel<PokemonListViewModel>()

    val pokemonListState = vm.pokemonListState.asStateFlow().collectAsState()

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

    vm.initViewModel(loadingState)
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
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(15.dp),
                painter = rememberImagePainter(
                    data = R.drawable.empty_state,
                ),
                contentDescription = "Pokedex empty state"
            )
            Text(text = stringResource(id = R.string.empty_state_refresh))
        }
    }
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
        Text(text = stringResource(id = R.string.random20))
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
        Text(text = stringResource(id = R.string.eraseDb))
    }
}