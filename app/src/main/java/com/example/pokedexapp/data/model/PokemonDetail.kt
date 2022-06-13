package com.example.pokedexapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonDetail")
data class PokemonDetail(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "height")
    val height: Double,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "location_area_encounters")
    val location_area_encounters: List<LocationAreas>?,
    @ColumnInfo(name = "sprites")
    val sprites: PokemonSprites,
    @ColumnInfo(name = "types")
    val types: List<PokemonTypeMacro>
)