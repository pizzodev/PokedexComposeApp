package com.example.pokedexapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.presentation.screens.TestViewModel
import com.example.pokedexapp.presentation.theme.PokedexAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView(this)
                }
            }
        }
    }
}

@Composable
fun MainView(mainActivity: MainActivity) {

    val vm = hiltViewModel<TestViewModel>()

    /*val pokemonListState = remember {
        mutableStateOf(emptyList<Pokemon>())
    }*/

    val pokemonListState = vm.pokemonListRefresh.collectAsState().value

    Column() {

        Row() {
            ReloadPokemon(vm)
            EraseDatabase(vm)
        }

        PokemonList(pokemonListState)
    }

    vm.initViewModel()

    //populatePokemonList(vm, pokemonListState)
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
fun EraseDatabase(vm: TestViewModel) {
    Surface(modifier =
    Modifier
        .size(50.dp)
        .clickable {
            vm.eraseDatabase()
        }) {
        Icon(
            Icons.Default.Clear,
            contentDescription = "...",
            tint = Color.Black,
        )
    }
}

@Composable
fun ReloadPokemon(vm: TestViewModel) {
    Surface(modifier =
    Modifier
        .size(50.dp)
        .clickable {
            vm.reloadList()
        }) {
        Icon(
            Icons.Default.AddCircle,
            contentDescription = "...",
            tint = Color.Black,
        )
    }
}
