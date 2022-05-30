package com.example.pokedexapp.data.network

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import com.example.pokedexapp.data.network.networkDto.PokemonDto
import com.example.pokedexapp.data.network.networkDto.PokemonListRoot
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface PokemonApi {
    @GET("pokemon")
    suspend fun getAllPokemon(@Query("offset") offset: Int): PokemonListRoot

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") pokemonName: String): PokemonDetailDto
}