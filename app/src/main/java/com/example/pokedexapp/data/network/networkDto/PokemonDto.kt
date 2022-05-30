package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.Pokemon
import com.google.gson.JsonObject
import java.util.*

data class PokemonListRoot(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
) {
    fun mapToPokemon(): Pokemon {
        return Pokemon(
            id = UUID.randomUUID(),
            name = this.name,
            url = this.url
        )
    }
}

data class PokemonDetailDto(
    val id: String,
    val name: String,
    val sprites: PokemonSpriteDto
)

data class PokemonSpriteDto(
    val front_default: String
)