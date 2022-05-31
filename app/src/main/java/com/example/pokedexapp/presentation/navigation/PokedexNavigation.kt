package com.example.pokedexapp.presentation.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexapp.presentation.screens.PokemonListScreen
import com.example.pokedexapp.presentation.screens.splash.PokedexSplashScreen

@Composable
fun PokedexNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexScreens.SplashScreen.name
    ) {

        composable(PokedexScreens.SplashScreen.name) {
            PokedexSplashScreen(navController)
        }

        composable(PokedexScreens.PokemonListScreen.name) {
            PokemonListScreen(navController)
        }

        //www.google.com/cityname="seattle"
        /*val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                })){ navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel,
                    city = city)
            }
        }*/

    }
}