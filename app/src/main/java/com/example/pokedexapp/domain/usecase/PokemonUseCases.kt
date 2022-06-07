package com.example.pokedexapp.domain.usecase

import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.datasource.PokemonRemoteRepository
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import javax.inject.Inject
import kotlin.random.Random

class PokemonUseCases @Inject constructor(
    private val pokemonDBRepo: PokemonDBRepository,
    private val pokemonRemoteRepo: PokemonRemoteRepository
) {
    suspend fun getAllPokemonUseCase(): List<Pokemon> {
        return pokemonDBRepo.getAllPokemon().ifEmpty {
            val remotePokemonList = pokemonRemoteRepo.getAllPokemonRemote(Random.nextInt(1126)).map {
                it.mapToPokemon()
            }
            pokemonDBRepo.saveToDatabase(remotePokemonList)

            remotePokemonList
        }
    }

    suspend fun getPokemonByNameUseCase(_name: String): PokemonDetail {
        val pokemonDB = pokemonDBRepo.getPokemonByName(_name)

        if (pokemonDB == null) {
            val pokemonDetail = pokemonRemoteRepo.getPokemonByName(_name).mapToPokemonDetail()
            pokemonDBRepo.saveToDatabase(pokemonDetail)
            return pokemonDetail
        }
        else
            return pokemonDB
    }

    suspend fun cleanupCache() {
        return pokemonDBRepo.eraseDabatabase()
    }
}
