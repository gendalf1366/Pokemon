package ru.gendalf13666.pokemon.features.details

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.gendalf13666.pokemon.repository.IRepository
import ru.gendalf13666.pokemon.repository.models.Pokemon
import ru.gendalf13666.pokemon.repository.models.PokemonInfo
import javax.inject.Inject

class DetailsPresenter(private val pokemon: Pokemon) : MvpPresenter<DetailsView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var repo: IRepository
    @Inject lateinit var uiThread: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() = repo.getPokemonByName(pokemon.name).observeOn(uiThread)
        .subscribe(
            {
                viewState.showData(it)
            },
            { error ->
                error.printStackTrace()
            }
        )

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}

@AddToEndSingle
interface DetailsView : MvpView {
    fun showData(data: PokemonInfo)
}
