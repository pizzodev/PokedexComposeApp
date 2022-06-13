package com.example.pokedexapp.presentation.ui.screens.pokemonList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonWithDetail
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

    var pokemonListState = MutableStateFlow<List<PokemonWithDetail>>(emptyList())

    fun initViewModel(
        _loadingState: MutableState<LoadingStatus>
    ) {
        if (loadingState == null) {
            loadingState = _loadingState
            populatePokemonListWithDetail()
        }
    }

    private fun populatePokemonListWithDetail() {
        try {
            viewModelScope.launch {
                loadingState?.value = LoadingStatus.LOADING

                val pokemonList = useCaseStorage.getPokemonWithDetailUseCase()
                pokemonListState.value = pokemonList

                loadingState?.value = LoadingStatus.COMPLETED
            }
        } catch (e: Exception) {
            loadingState?.value = LoadingStatus.FAILED
            Log.d("Err", "Cannot do this: ${e.localizedMessage}")
        }
    }

    private fun showEmptyState() {
        viewModelScope.launch {
            loadingState?.value = LoadingStatus.LOADING
            pokemonListState?.value = emptyList()
            loadingState?.value = LoadingStatus.COMPLETED
        }

    }

    fun reloadList() {
        try {
            viewModelScope.launch {
                populatePokemonListWithDetail()
            }
        } catch (e: Exception) {
            Log.d("Room Err", "Cannot delete entries: ${e.localizedMessage}")
        }
    }

    fun eraseDatabase() {
        try {
            viewModelScope.launch {
                useCaseStorage.cleanupCache()
                showEmptyState()
            }
        } catch (e: Exception) {
            Log.d("Room Err", "Cannot delete entries: ${e.localizedMessage}")
        }
    }

}