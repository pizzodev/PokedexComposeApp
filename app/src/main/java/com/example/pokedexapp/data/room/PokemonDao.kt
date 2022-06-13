package com.example.pokedexapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedexapp.data.model.*

@Dao
interface PokemonDao {
    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)

    @Insert
    suspend fun insertPokemonDetail(pokemonDetail: PokemonDetail)

    @Query("SELECT * from pokemon")
    suspend fun getAllPokemon(): List<Pokemon>

    @Query("SELECT * from pokemonDetail where name = :name")
    suspend fun getPokemonByName(name: String): PokemonDetail?

    @Query("DELETE from pokemon")
    suspend fun eraseDatabase()
}