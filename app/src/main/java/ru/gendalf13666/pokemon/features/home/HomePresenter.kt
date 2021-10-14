package ru.gendalf13666.pokemon.features.home

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.gendalf13666.pokemon.features.shared.IAdapterPresenter
import ru.gendalf13666.pokemon.features.shared.IItemView
import ru.gendalf13666.pokemon.navigation.IScreens
import ru.gendalf13666.pokemon.repository.IRepository
import ru.gendalf13666.pokemon.repository.models.Pokemon
import javax.inject.Inject

class HomePresenter : MvpPresenter<HomeView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var repo: IRepository
    @Inject lateinit var uiThread: Scheduler
    @Inject lateinit var screens: IScreens

    val adapterPresenter = AdapterPresenter()
    private lateinit var pokemons: List<Pokemon>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        adapterPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.details(pokemons[itemView.pos]))
        }
    }

    private fun loadData() = repo.getPokemons().observeOn(uiThread)
        .subscribe(
            { newList ->
                pokemons = newList
                adapterPresenter.pokemonList.clear()
                adapterPresenter.pokemonList.addAll(newList)
                viewState.updateList()
                viewState.showData()
            },
            { error ->
                error.printStackTrace()
            }
        )

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

    class AdapterPresenter : IHomeAdapter {
        val pokemonList = mutableListOf<Pokemon>()

        override var itemClickListener: ((IPokemonItem) -> Unit)? = null

        override fun bindView(view: IPokemonItem) {
            val pokemon = pokemonList[view.pos]
            view.setTitle(pokemon.name)
            view.setImage(pokemon.getImageUrl())
        }

        override fun getCount() = pokemonList.size
    }
}

@AddToEndSingle
interface HomeView : MvpView {
    fun init()
    fun updateList()
    fun showLoading()
    fun showData()
}

interface IPokemonItem : IItemView {
    fun setTitle(txt: String)
    fun setImage(url: String)
}

interface IHomeAdapter : IAdapterPresenter<IPokemonItem>
