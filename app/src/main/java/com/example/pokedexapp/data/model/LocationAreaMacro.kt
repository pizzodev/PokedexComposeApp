package com.example.pokedexapp.data.model

data class LocationAreas(
    val locationAreaId: Int,
    var locationAreaUrl: String,
    var locationAreaName: String
)

data class LocationAreaMacro(
    var locationArea: LocationAreaMicro
)

data class LocationAreaMicro(
    var name: String,
    var url: String
)