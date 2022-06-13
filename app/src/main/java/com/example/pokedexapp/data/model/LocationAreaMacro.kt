package com.example.pokedexapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "locationAreas")
data class LocationAreas(
    //@PrimaryKey(autoGenerate = true)
    val locationAreaId: Int,
    //@ColumnInfo(name = "locationAreaUrl")
    var locationAreaUrl: String,
    //@ColumnInfo(name = "locationAreaName")
    var locationAreaName: String
)

data class LocationAreaMacro(
    var locationArea: LocationAreaMicro
)

data class LocationAreaMicro(
    var name: String,
    var url: String
)