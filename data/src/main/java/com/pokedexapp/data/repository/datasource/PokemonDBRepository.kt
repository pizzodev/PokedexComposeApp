package com.pokedexapp.data.repository.datasource

import com.pokedexapp.data.model.Pokemon
import com.pokedexapp.data.room.PokemonDao
import javax.inject.Inject

class PokemonDBRepository @Inject constructor(private val pokemonDao: PokemonDao) {
    suspend fun getAllPokemon(): List<Pokemon> = pokemonDao.getAllPokemon()
}
