package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.PokemonSprites

data class PokemonSpritesDto(
    val front_default: String
) {
    fun mapToPokemonSprites(): PokemonSprites {
        return PokemonSprites(
            front_default = this.front_default,
        )
    }
}