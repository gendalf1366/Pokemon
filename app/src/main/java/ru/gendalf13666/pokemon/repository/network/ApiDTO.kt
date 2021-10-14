package ru.gendalf13666.pokemon.repository.network

import com.google.gson.annotations.SerializedName
import ru.gendalf13666.pokemon.repository.models.Pokemon
import ru.gendalf13666.pokemon.repository.persistence.PokemonEntity

data class ApiDTO(
    @field:SerializedName("count") val count: Long,
    @field:SerializedName("next") val next: String?,
    @field:SerializedName("previous") val previous: String?,
    @field:SerializedName("results") val results: List<PokemonData>,
) {
    fun convertToDomain(): List<Pokemon> = results.map { it.convertToDomain() }
    fun convertToDatabase(): List<PokemonEntity> = results.map { it.convertToDatabase() }
}
