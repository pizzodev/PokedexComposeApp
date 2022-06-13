package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.PokemonTypeMacro
import com.example.pokedexapp.data.model.PokemonTypeMicro

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