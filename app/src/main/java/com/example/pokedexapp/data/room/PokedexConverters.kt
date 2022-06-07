package com.example.pokedexapp.data.room

import androidx.room.TypeConverter
import com.example.pokedexapp.data.model.PokemonSprites
import com.google.gson.Gson

class PokedexConverters {
    @TypeConverter
    fun toSpritesStringified(value: PokemonSprites): String {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun fromSpritesStringified(value: String): PokemonSprites {
        return Gson().fromJson(value, PokemonSprites::class.java)
    }
}