package ru.gendalf13666.pokemon.repository.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemons(): Single<ApiDTO>

    @GET("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Single<InfoData>

    companion object { const val BASE_URL = "https://pokeapi.co/api/v2/" }
}
