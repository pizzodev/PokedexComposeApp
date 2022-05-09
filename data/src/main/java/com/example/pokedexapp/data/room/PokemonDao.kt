package com.example.pokedexapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexapp.data.model.Pokemon

@Dao
interface PokemonDao {
    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * from pokemon")
    suspend fun getAllPokemon(): List<Pokemon>
}