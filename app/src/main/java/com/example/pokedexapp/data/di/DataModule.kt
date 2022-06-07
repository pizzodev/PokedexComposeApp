package com.example.pokedexapp.data.di;

import android.content.Context;
import android.content.res.Resources
import androidx.room.Room;
import com.example.pokedexapp.R
import com.example.pokedexapp.data.network.PokemonApi
import com.example.pokedexapp.data.room.PokedexConverters
import com.example.pokedexapp.data.room.PokemonDao;
import com.example.pokedexapp.data.room.PokemonDatabase;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase:PokemonDatabase): PokemonDao
            = pokemonDatabase.pokemonDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context): PokemonDatabase
    = Room.databaseBuilder(
        context,
        PokemonDatabase::class.java,
        "pokemon_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }
}
