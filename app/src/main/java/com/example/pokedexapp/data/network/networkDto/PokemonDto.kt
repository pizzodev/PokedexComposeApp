package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.model.PokemonSprites
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
    val sprites: PokemonSpritesDto
) {
    fun mapToPokemonDetail(): PokemonDetail {
        return PokemonDetail(
            id = this.id,
            name = this.name,
            sprites = this.sprites.mapToPokemonSprites()
        )
    }
}

data class PokemonSpritesDto(
    val front_default: String
) {
    fun mapToPokemonSprites(): PokemonSprites {
        return PokemonSprites(
            front_default = this.front_default
        )
    }
}