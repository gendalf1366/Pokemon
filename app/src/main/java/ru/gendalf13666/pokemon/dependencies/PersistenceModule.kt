package ru.gendalf13666.pokemon.dependencies

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.gendalf13666.pokemon.PokemonApp
import ru.gendalf13666.pokemon.repository.persistence.DataBase
import javax.inject.Named
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Provides
    @Named("databaseName")
    fun baseUrlProvider(): String = DataBase.DATABASE_NAME

    @Singleton
    @Provides
    fun databaseProvider(app: PokemonApp, @Named("databaseName") name: String): DataBase =
        Room.databaseBuilder(app, DataBase::class.java, name).build()
}
