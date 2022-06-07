package com.example.pokedexapp.presentation.screens.pokemonDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.domain.usecase.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val useCaseStorage: PokemonUseCases
): ViewModel() {

    private var name: String? = null
    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(value = null)
    val pokemonDetailRefresh = _pokemonDetail.asStateFlow()

    fun initViewModel(_name: String) {
        if (name == null) {
            name = _name
            retrievePokemonDetail(_name)
        }
    }

    //TODO error handling to be done better
    private fun retrievePokemonDetail(_name: String) {
        viewModelScope.launch {
            try {
                val pokemonDetail = useCaseStorage.getPokemonByNameUseCase(_name)
                _pokemonDetail.value = pokemonDetail
            } catch (e: Exception) {
                Log.d("Err", "Cannot do this: ${e.localizedMessage}")
            }
        }
    }

}