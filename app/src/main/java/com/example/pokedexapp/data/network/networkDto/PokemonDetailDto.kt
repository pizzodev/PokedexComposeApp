package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.LocationAreas
import com.example.pokedexapp.data.model.PokemonDetail

data class PokemonDetailDto(
    val id: String,
    val name: String,
    val height: Double,
    val weight: Double,
    val location_area_encounters: String,
    var location_area: MutableList<LocationAreas>,
    val sprites: PokemonSpritesDto,
    val types: List<PokemonTypeMacroDto>
) {
    fun mapToPokemonDetail(): PokemonDetail {
        return PokemonDetail(
            id = this.id,
            name = this.name,
            height = this.height / 10,
            weight = this.weight / 10,
            location_area_encounters = this.location_area,
            sprites = this.sprites.mapToPokemonSprites(),
            types = this.types.map { it.mapToPokemonTypeMacro() }
        )
    }
}