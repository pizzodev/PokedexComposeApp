package com.example.pokedexapp.data.datasource

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.room.PokemonDao
import java.util.*
import javax.inject.Inject

class PokemonDBRepository @Inject constructor(
    private val pokemonDao: PokemonDao
) {

    val DUMMY_POKEMON = Pokemon(
        id = UUID.randomUUID(),
        name = "Dummy pokemon",
        url = ""
    )

    suspend fun getAllPokemon(): List<Pokemon> = pokemonDao.getAllPokemon()

    suspend fun saveToDatabase(pokemonList: List<Pokemon>) {
        pokemonList.forEach { pokemonToInsert ->
            pokemonDao.insertPokemon(pokemonToInsert)
        }
    }

    suspend fun eraseDabatabase() = pokemonDao.eraseDatabase()
}
