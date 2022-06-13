package com.example.pokedexapp.domain.usecase

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.datasource.PokemonRemoteRepository
import com.example.pokedexapp.data.model.Pokemon
import com.example.pokedexapp.data.model.PokemonDetail
import com.example.pokedexapp.data.model.PokemonWithDetail
import com.example.pokedexapp.data.network.networkDto.PokemonDetailDto
import javax.inject.Inject
import kotlin.random.Random

class PokemonUseCases @Inject constructor(
    private val pokemonDBRepo: PokemonDBRepository,
    private val pokemonRemoteRepo: PokemonRemoteRepository
) {

    suspend fun getPokemonWithDetailUseCase(): List<PokemonWithDetail> {

        val pokemonList = pokemonDBRepo.getAllPokemon().ifEmpty {
            val remotePokemonList = pokemonRemoteRepo.getAllPokemonRemote(Random.nextInt(1126))
            pokemonDBRepo.saveToDatabase(remotePokemonList)
            remotePokemonList.map { it.mapToPokemon() }
        }

        return pokemonList.map { pkm ->

            val detail = getPokemonByNameUseCase(_name = pkm.name)

            PokemonWithDetail(
                detail.id,
                pkm.name,
                pkm.url,
                detail.sprites,
                detail.types
            )
        }

    }

    suspend fun getPokemonByNameUseCase(_name: String): PokemonDetail {
        val pokemonDB = pokemonDBRepo.getPokemonByName(_name)

        pokemonDB?.let {
            return it
        }?: kotlin.run {
            val pokemonDetail = pokemonRemoteRepo.getPokemonByName(_name)

            //pokemonDBRepo.saveToDatabase(pokemonDetail)

            return pokemonDetail.mapToPokemonDetail()
        }
    }

    suspend fun cleanupCache() {
        return pokemonDBRepo.eraseDabatabase()
    }
}
