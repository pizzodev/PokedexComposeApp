package com.example.pokedexapp.presentation.ui.screens.pokemonDetail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.model.PokemonWithDetail
import com.example.pokedexapp.domain.usecase.PokemonUseCases
import com.example.pokedexapp.presentation.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val useCaseStorage: PokemonUseCases
): ViewModel() {

    private var name: String? = null
    var loadingState: MutableState<LoadingStatus>? = null

    var pokemonDetailState = MutableStateFlow<PokemonDetail?>(null)

    fun initViewModel(
        _loadingState: MutableState<LoadingStatus>,
        _name: String
    ) {
        if (name == null) {
            name = _name
            loadingState = _loadingState
            retrievePokemonDetail(_name)
        }
    }

    private fun retrievePokemonDetail(_name: String) {
        try {
            viewModelScope.launch {
                loadingState?.value = LoadingStatus.LOADING

                val pokemonDetail = useCaseStorage.getPokemonByNameUseCase(_name)
                pokemonDetailState.value = pokemonDetail

                loadingState?.value = LoadingStatus.COMPLETED
            }
        } catch (e: Exception) {
            loadingState?.value = LoadingStatus.FAILED
            Log.d("Err", "Cannot do this: ${e.localizedMessage}")
        }
    }
}