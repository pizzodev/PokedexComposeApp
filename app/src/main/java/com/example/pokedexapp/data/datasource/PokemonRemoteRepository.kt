package com.example.pokedexapp.data.datasource

import android.util.Log
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.network.PokemonApi
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import com.example.pokedexapp.data.network.networkDto.PokemonDto
import com.example.pokedexapp.data.room.PokemonDao
import java.util.*
import javax.inject.Inject

class PokemonRemoteRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {

    suspend fun getAllPokemonRemote(offset: Int): List<PokemonDto> {
        val pokemonListRmt = pokemonApi.getAllPokemon(offset).results
        //val firstDetail = pokemonApi.getPokemonDetail(pokemonListRmt[0].name)

        //Log.d("PokeIMG ${firstDetail.name}", firstDetail.sprites.front_default)

        return pokemonListRmt
    }

    suspend fun getPokemonByName(_name: String): PokemonDetailDto {
        return pokemonApi.getPokemonDetail(_name)
    }
}
