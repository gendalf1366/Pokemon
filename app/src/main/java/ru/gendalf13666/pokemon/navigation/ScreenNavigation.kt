package ru.gendalf13666.pokemon.navigation

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gendalf13666.pokemon.features.details.DetailsFragment
import ru.gendalf13666.pokemon.features.home.HomeFragment
import ru.gendalf13666.pokemon.repository.models.Pokemon

class ScreenNavigation : IScreens {
    override fun home(): Screen =
        FragmentScreen { HomeFragment.newInstance() }

    override fun details(data: Pokemon): Screen =
        FragmentScreen { DetailsFragment.newInstance(data) }
}
