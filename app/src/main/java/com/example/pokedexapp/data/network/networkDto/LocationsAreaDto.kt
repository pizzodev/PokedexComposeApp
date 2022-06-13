package com.example.pokedexapp.data.network.networkDto

import com.example.pokedexapp.data.model.LocationAreas

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