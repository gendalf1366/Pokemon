package ru.gendalf13666.pokemon.features

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.gendalf13666.pokemon.PokemonApp
import ru.gendalf13666.pokemon.R
import ru.gendalf13666.pokemon.navigation.INavigationUpListener
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {
    @Inject lateinit var navHolder: NavigatorHolder
    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter {
        MainPresenter().apply {
            PokemonApp.INSTANCE.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        PokemonApp.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is INavigationUpListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}
