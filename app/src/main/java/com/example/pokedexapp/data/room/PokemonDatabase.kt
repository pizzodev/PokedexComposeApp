package com.example.pokedexapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexapp.data.model.*

@Database(entities = [Pokemon::class, PokemonDetail::class], version = 1, exportSchema = false)
@TypeConverters(PokedexConverters::class)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}