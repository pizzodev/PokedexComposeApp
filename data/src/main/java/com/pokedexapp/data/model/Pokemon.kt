package com.pokedexapp.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int
)