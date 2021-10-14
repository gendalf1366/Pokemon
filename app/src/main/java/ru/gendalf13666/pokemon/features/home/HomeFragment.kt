package ru.gendalf13666.pokemon.features.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.gendalf13666.pokemon.PokemonApp
import ru.gendalf13666.pokemon.navigation.INavigationUpListener
import ru.gendalf13666.pokemon.R
import ru.gendalf13666.pokemon.databinding.FragmentHomeBinding

class HomeFragment :
    MvpAppCompatFragment(R.layout.fragment_home), HomeView, INavigationUpListener {
    private val presenter by moxyPresenter {
        HomePresenter().apply { PokemonApp.INSTANCE.appComponent.inject(this) }
    }

    private var adapter: HomeAdapter? = null

    private var binding: FragmentHomeBinding? = null
    private val b get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
    }

    override fun init() {
        adapter = HomeAdapter(presenter.adapterPresenter)
        b.rvContainer.adapter = adapter
        b.rvContainer.layoutManager =
            GridLayoutManager(requireContext(), 2)
    }

    override fun updateList() { adapter?.notifyDataSetChanged() }

    override fun showLoading() = with(b) {
        pbLoading.isVisible = true
        rvContainer.isVisible = false
    }

    override fun showData() = with(b) {
        pbLoading.isVisible = false
        rvContainer.isVisible = true
    }

    override fun backPressed() = presenter.backClicked()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
