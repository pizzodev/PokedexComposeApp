package com.example.pokedexapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexapp.presentation.screens.PokemonListScreen
import com.example.pokedexapp.presentation.ui.screens.pokemonDetail.PokemonDetailScreen
import com.example.pokedexapp.presentation.ui.screens.splash.PokedexSplashScreen
import com.example.pokedexapp.presentation.utils.LoadingStatus

@Composable
fun PokedexNavigation(loadingState: MutableState<LoadingStatus>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexScreens.SplashScreen.name
    ) {

        composable(route = PokedexScreens.SplashScreen.name) {
            PokedexSplashScreen(navController)
        }

        composable(route = PokedexScreens.PokemonListScreen.name) {
            PokemonListScreen(navController, loadingState)
        }

        val detailRoute = PokedexScreens.PokemonDetailScreen.name
        composable(
            route = "$detailRoute/{name}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("name").let { name ->
                PokemonDetailScreen(navController, loadingState, name?:"")
            }
        }

    }
}