package com.example.pokedexapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.pokedexapp.data.room.PokedexConverters

@Entity(tableName = "pokemonDetail")
data class PokemonDetail(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sprites")
    val sprites: PokemonSprites,
    @ColumnInfo(name = "types")
    val types: List<PokemonTypeMacro>
)