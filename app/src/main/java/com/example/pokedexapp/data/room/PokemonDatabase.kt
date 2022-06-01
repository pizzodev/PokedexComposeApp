package com.example.pokedexapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.model.PokemonSprite

@Database(entities = [Pokemon::class, PokemonDetail::class], version = 2, exportSchema = false)
@TypeConverters(PokedexConverters::class)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}