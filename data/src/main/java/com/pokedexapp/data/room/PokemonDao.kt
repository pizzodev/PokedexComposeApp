package com.pokedexapp.data.room

import androidx.room.Dao
import androidx.room.Query
import com.pokedexapp.data.model.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * from pokemon")
    suspend fun getAllPokemon(): List<Pokemon>
}