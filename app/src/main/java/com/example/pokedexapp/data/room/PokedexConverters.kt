package com.example.pokedexapp.data.room

import androidx.room.TypeConverter
import com.example.pokedexapp.data.model.PokemonSprite
import com.google.gson.Gson

class PokedexConverters {
    @TypeConverter
    fun fromSpriteEntity(value: PokemonSprite): String {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toSpriteEntity(value: String): PokemonSprite {
        return Gson().fromJson(value, PokemonSprite::class.java)
    }
}