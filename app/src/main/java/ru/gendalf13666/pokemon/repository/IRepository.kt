package ru.gendalf13666.pokemon.repository

import io.reactivex.rxjava3.core.Single
import ru.gendalf13666.pokemon.repository.models.Pokemon
import ru.gendalf13666.pokemon.repository.models.PokemonInfo

interface IRepository {
    fun getPokemons(): Single<List<Pokemon>>
    fun getPokemonByName(name: String): Single<PokemonInfo>
}
