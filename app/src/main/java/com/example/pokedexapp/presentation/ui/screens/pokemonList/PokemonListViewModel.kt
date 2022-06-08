package com.example.pokedexapp.presentation.ui.screens.pokemonList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.domain.usecase.PokemonUseCases
import com.example.pokedexapp.presentation.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val useCaseStorage: PokemonUseCases
): ViewModel() {

    var loadingState: MutableState<LoadingStatus>? = null

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonListRefresh = _pokemonList.asStateFlow()

    fun initViewModel(_loadingState: MutableState<LoadingStatus>) {
        loadingState = _loadingState
        populatePokemonList()
    }

    private fun populatePokemonList() {
        try {
            viewModelScope.launch {
                loadingState?.value = LoadingStatus.LOADING

                val pokemonList = useCaseStorage.getAllPokemonUseCase()
                _pokemonList.value = pokemonList

                loadingState?.value = LoadingStatus.COMPLETED
            }
        } catch (e: Exception) {
            loadingState?.value = LoadingStatus.FAILED
            Log.d("Err", "Cannot do this: ${e.localizedMessage}")
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