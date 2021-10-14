package ru.gendalf13666.pokemon.dependencies

import dagger.Module
import dagger.Provides
import ru.gendalf13666.pokemon.repository.IRepository
import ru.gendalf13666.pokemon.repository.Repository
import ru.gendalf13666.pokemon.repository.network.PokemonService
import ru.gendalf13666.pokemon.repository.persistence.DataBase
import ru.gendalf13666.pokemon.util.INetworkStatus

@Module
class RepositoryModule {
    @Provides
    fun repositoryProvider(
        db: DataBase,
        service: PokemonService,
        network: INetworkStatus
    ): IRepository = Repository(db, service, network)
}
