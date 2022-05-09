package com.example.pokedexapp.data.di;

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
            PokemonDatabase::class,
            "pokemon_database"
        ).fallbackToDestructiveMigration().build()
}
