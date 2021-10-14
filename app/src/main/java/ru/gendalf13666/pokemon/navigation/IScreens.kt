package ru.gendalf13666.pokemon.navigation

import com.github.terrakok.cicerone.Screen
import ru.gendalf13666.pokemon.repository.models.Pokemon

interface IScreens {
    fun home(): Screen
    fun details(data: Pokemon): Screen
}
