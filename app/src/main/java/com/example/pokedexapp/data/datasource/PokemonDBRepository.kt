package com.example.pokedexapp.data.datasource

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.room.PokemonDao
import java.util.*
import javax.inject.Inject

class PokemonDBRepository @Inject constructor(private val pokemonDao: PokemonDao) {

    val DUMMY_POKEMON = Pokemon(
        id = UUID.randomUUID(),
        name = "Dummy pokemon"
    )

    suspend fun insertDummyPokemon(): Unit = pokemonDao.insertPokemon(DUMMY_POKEMON)

    suspend fun getAllPokemon(): List<Pokemon> = pokemonDao.getAllPokemon()
}
