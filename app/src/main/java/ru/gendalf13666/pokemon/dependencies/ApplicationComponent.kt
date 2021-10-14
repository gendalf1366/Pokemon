package ru.gendalf13666.pokemon.dependencies

import dagger.Component
import ru.gendalf13666.pokemon.features.MainActivity
import ru.gendalf13666.pokemon.features.MainPresenter
import ru.gendalf13666.pokemon.features.details.DetailsPresenter
import ru.gendalf13666.pokemon.features.home.HomePresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {
    fun inject(MainActivity: MainActivity)
    fun inject(MainPresenter: MainPresenter)
    fun inject(HomePresenter: HomePresenter)
    fun inject(DetailsPresenter: DetailsPresenter)
}
