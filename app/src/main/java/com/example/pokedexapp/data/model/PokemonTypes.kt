package com.example.pokedexapp.data.model

data class PokemonTypeMacro(
    val slot: Int,
    val type: PokemonTypeMicro
)

data class PokemonTypeMicro(
    val name: String,
    val url: String
)