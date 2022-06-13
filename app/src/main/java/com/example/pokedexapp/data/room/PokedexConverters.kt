package com.example.pokedexapp.data.room

import androidx.room.TypeConverter
import com.example.pokedexapp.data.model.PokemonSprites
import com.example.pokedexapp.data.model.PokemonTypeMacro
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

    @TypeConverter
    fun toPokemonTypeMacroStringified(value: List<PokemonTypeMacro>): String {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun fromPokemonTypeMacroStringified(value: String): List<PokemonTypeMacro> {
        return Gson().fromJson(value, Array<PokemonTypeMacro>::class.java).toList()
    }
}