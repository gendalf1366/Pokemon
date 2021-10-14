package ru.gendalf13666.pokemon.dependencies

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.gendalf13666.pokemon.PokemonApp
import javax.inject.Singleton

@Module
class ApplicationModule(val app: PokemonApp) {
    @Provides
    fun appProvider(): PokemonApp = app

    @Singleton
    @Provides
    fun uiSchedulerProvider(): Scheduler = AndroidSchedulers.mainThread()
}
