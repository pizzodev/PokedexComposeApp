package com.example.pokedexapp.domain.usecase

import com.example.pokedexapp.data.datasource.PokemonDBRepository
import com.example.pokedexapp.data.datasource.PokemonRemoteRepository
import com.example.pokedexapp.data.model.*
import javax.inject.Inject
import kotlin.random.Random

class PokemonUseCases @Inject constructor(
    private val pokemonDBRepo: PokemonDBRepository,
    private val pokemonRemoteRepo: PokemonRemoteRepository
) {

    suspend fun getPokemonWithDetailUseCase(pokemonListState: MutableStateFlow<List<PokemonWithDetail>>) {

        val pokemonList = pokemonDBRepo.getAllPokemon().ifEmpty {
            val remotePokemonList = pokemonRemoteRepo.getAllPokemonRemote(Random.nextInt(1126))

            //Run on separate coroutine
            coroutineScope {
                pokemonDBRepo.saveToDatabase(remotePokemonList)
            }

            remotePokemonList.map { it.mapToPokemon() }
        }

        val pokemonListWithDetail = mutableListOf<PokemonWithDetail>()

        //Run on n threads
        coroutineScope {
            JobMultiThreadHandler.waitForCompletion(
                    pokemonList.map { pkm ->
                        launch {
                            val detail = getPokemonByNameUseCase(_name = pkm.name)
                            pokemonListWithDetail.add(
                                PokemonWithDetail(
                                    detail.id,
                                    pkm.name,
                                    pkm.url,
                                    detail.sprites,
                                    detail.types
                                )
                            )
                        }
                    }
            )
        }

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
            val pokemonDetail = pokemonRemoteRepo.getPokemonByName(_name).apply {
                location_area = mutableListOf()

                getLocationEncounterAreas(
                    this.location_area_encounters
                ).map {
                    this.location_area.add(it)
                }
            }

            pokemonDBRepo.saveToDatabase(pokemonDetail)

            return pokemonDetail.mapToPokemonDetail()
        }
    }

    suspend fun getLocationEncounterAreas(_url: String): List<LocationAreas> {
        val locationAreas = pokemonRemoteRepo.getLocationAreas(_url)
        return locationAreas.map { it.mapToLocationArea() }
    }

    suspend fun cleanupCache() {
        return pokemonDBRepo.eraseDabatabase()
    }
}
