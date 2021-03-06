package com.example.pokedexapp.data.datasource

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import com.example.pokedexapp.data.network.networkDto.PokemonDto
import com.example.pokedexapp.data.room.PokemonDao
import javax.inject.Inject

class PokemonDBRepository @Inject constructor(
    private val pokemonDao: PokemonDao
) {

    suspend fun getAllPokemon(): List<Pokemon> = pokemonDao.getAllPokemon()

    suspend fun getPokemonByName(_name: String): PokemonDetail? = pokemonDao.getPokemonByName(_name)

    suspend fun saveToDatabase(pokemonList: List<PokemonDto>) {
        pokemonList.forEach { pokemonToInsert ->
            pokemonDao.insertPokemon(pokemonToInsert.mapToPokemon())
        }
    }

    suspend fun saveToDatabase(pokemonDetail: PokemonDetailDto) {
        pokemonDao.insertPokemonDetail(pokemonDetail.mapToPokemonDetail())
    }

    suspend fun eraseDabatabase() = pokemonDao.eraseDatabase()
}
