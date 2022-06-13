package com.example.pokedexapp.data.datasource

import com.example.pokedexapp.data.network.PokemonApi
import com.example.pokedexapp.data.network.networkDto.LocationAreasDto
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import com.example.pokedexapp.data.network.networkDto.PokemonDto
import javax.inject.Inject

class PokemonRemoteRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {

    suspend fun getAllPokemonRemote(offset: Int): List<PokemonDto> {
        val pokemonListRmt = pokemonApi.getAllPokemon(offset).results

        return pokemonListRmt
    }

    suspend fun getPokemonByName(_name: String): PokemonDetailDto {
        return pokemonApi.getPokemonDetail(_name)
    }

    suspend fun getLocationAreas(_url: String): List<LocationAreasDto> {
        return pokemonApi.getLocationAreas(_url).map { it.mapToLocationAreaDto() }
    }
}
