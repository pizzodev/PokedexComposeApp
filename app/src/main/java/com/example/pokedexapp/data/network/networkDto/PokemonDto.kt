package com.example.pokedexapp.data.network.networkDto

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.pokedexapp.data.model.*
import java.nio.DoubleBuffer
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

data class PokemonSpritesDto(
    val front_default: String
) {
    fun mapToPokemonSprites(): PokemonSprites {
        return PokemonSprites(
            front_default = this.front_default,
        )
    }
}

data class LocationAreasDto(
    val locationAreaId: Int,
    var locationAreaUrl: String,
    var locationAreaName: String
) {
    fun mapToLocationArea(): LocationAreas {
        return LocationAreas(
            locationAreaId = this.locationAreaId,
            locationAreaName = this.locationAreaName,
            locationAreaUrl = this.locationAreaUrl
        )
    }
}

data class LocationAreaMacroDto(
    val location_area : LocationAreaMicroDto
) {
    fun mapToLocationArea(): LocationAreas {
        return LocationAreas(
            locationAreaId = this.location_area.id,
            locationAreaUrl = this.location_area.url,
            locationAreaName = this.location_area.name
        )
    }
    fun mapToLocationAreaDto(): LocationAreasDto {
        return LocationAreasDto(
            locationAreaId = this.location_area.id,
            locationAreaUrl = this.location_area.url,
            locationAreaName = this.location_area.name
        )
    }
}

data class LocationAreaMicroDto(
    var id: Int,
    var name: String,
    var url: String
)

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