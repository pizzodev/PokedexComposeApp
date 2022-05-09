package com.example.pokedexapp.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: PokemonDBRepository
): ViewModel() {

    suspend fun initViewModel(): List<Pokemon> {
        repository.insertDummyPokemon()
        return repository.getAllPokemon()
    }

}