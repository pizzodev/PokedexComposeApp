package com.example.pokedexapp.presentation.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.domain.usecase.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val useCaseStorage: PokemonUseCases
): ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonListRefresh = _pokemonList.asStateFlow()

    fun initViewModel() {
        populatePokemonList()
    }

    //TODO error handling to be done better
    fun populatePokemonList() {
        viewModelScope.launch {
            try {
                val pokemonList = useCaseStorage.getAllPokemonUseCase()
                _pokemonList.value = pokemonList
            } catch (e: Exception) {
                Log.d("Err", "Cannot do this: ${e.localizedMessage}")
            }
        }
    }

    fun reloadList() {
        try {
            viewModelScope.launch {
                populatePokemonList()
            }
        } catch (e: Exception) {
            Log.d("Room Err", "Cannot delete entries: ${e.localizedMessage}")
        }
    }

    fun eraseDatabase() {
        try {
            viewModelScope.launch {
                useCaseStorage.cleanupCache()
                populatePokemonList()
            }
        } catch (e: Exception) {
            Log.d("Room Err", "Cannot delete entries: ${e.localizedMessage}")
        }
    }

}