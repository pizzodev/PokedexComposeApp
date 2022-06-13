package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.*
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
    val sprites: PokemonSpritesDto,
    val types: List<PokemonTypeMacroDto>
) {
    fun mapToPokemonDetail(): PokemonDetail {
        return PokemonDetail(
            id = this.id,
            name = this.name,
            sprites = this.sprites.mapToPokemonSprites(),
            types = this.types.map { it.mapToPokemonTypeMacro() }
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

data class PokemonTypeMacroDto(
    val slot: Int,
    val type: PokemonTypeMicroDto
) {
    fun mapToPokemonTypeMacro(): PokemonTypeMacro {
        return PokemonTypeMacro(
            slot = this.slot,
            type = this.type.mapToPokemonTypeMicro()
        )
    }
}

data class PokemonTypeMicroDto (
    val name: String,
    val url: String
) {
    fun mapToPokemonTypeMicro(): PokemonTypeMicro {
        return PokemonTypeMicro(
            name = this.name,
            url = this.url
        )
    }
}