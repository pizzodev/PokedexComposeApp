package com.example.pokedexapp.data.datasource

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.room.PokemonDao
import java.util.*
import javax.inject.Inject

class PokemonDBRepository @Inject constructor(
    private val pokemonDao: PokemonDao
) {

    suspend fun getAllPokemon(): List<Pokemon> = pokemonDao.getAllPokemon()

    suspend fun getPokemonByName(_name: String): PokemonDetail? = pokemonDao.getPokemonByName(_name)

    suspend fun saveToDatabase(pokemonList: List<Pokemon>) {
        pokemonList.forEach { pokemonToInsert ->
            pokemonDao.insertPokemon(pokemonToInsert)
        }
    }

    suspend fun saveToDatabase(pokemonDetail: PokemonDetail) {
        pokemonDao.insertPokemonDetail(pokemonDetail)
    }

    suspend fun eraseDabatabase() = pokemonDao.eraseDatabase()
}
