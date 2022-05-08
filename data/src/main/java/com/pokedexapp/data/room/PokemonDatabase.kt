package com.pokedexapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokedexapp.data.model.Pokemon

@Database(entities = [Pokemon::class, Unit::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}