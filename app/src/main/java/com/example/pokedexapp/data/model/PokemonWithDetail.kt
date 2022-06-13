package com.example.pokedexapp.data.model

data class PokemonWithDetail(
    val id: String,
    val name: String,
    val url: String,
    val sprites: PokemonSprites,
    val types: List<PokemonTypeMacro>
)