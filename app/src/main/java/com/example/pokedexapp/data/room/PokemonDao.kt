package com.example.pokedexapp.data.room

import androidx.room.Dao
import androidx.room.Delete
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

    /*@Insert
    suspend fun insertLocationArea(locationAreaMacro: LocationAreas)*/

    /*@Query("SELECT * from locationAreas where locationAreaName = :name")
    suspend fun getLocationArea(name: String): LocationAreas?*/

    @Query("DELETE from pokemon")
    suspend fun eraseDatabase()
}