package ru.gendalf13666.pokemon

import android.app.Application
import ru.gendalf13666.pokemon.dependencies.ApplicationComponent
import ru.gendalf13666.pokemon.dependencies.ApplicationModule
import ru.gendalf13666.pokemon.dependencies.DaggerApplicationComponent

class PokemonApp : Application() {
    companion object {
        lateinit var INSTANCE: PokemonApp
    }

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        appComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
