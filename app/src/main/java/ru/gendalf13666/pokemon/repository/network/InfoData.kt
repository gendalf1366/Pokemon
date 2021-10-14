package ru.gendalf13666.pokemon.repository.network

import com.google.gson.annotations.SerializedName
import ru.gendalf13666.pokemon.repository.models.PokemonInfo
import ru.gendalf13666.pokemon.repository.persistence.PokemonEntity

data class InfoData(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("weight") val weight: Int,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("base_experience") val experience: Int,
    @field:SerializedName("location_area_encounters") val area: String,

    @field:SerializedName("forms") val forms: List<FieldItem>,
    @field:SerializedName("abilities") val abilities: List<FieldAbility>,
    @field:SerializedName("game_indices") val indexes: List<FieldIndex>,
    @field:SerializedName("species") val species: FieldItem,
    @field:SerializedName("stats") val stats: List<FieldStatistics>,
    @field:SerializedName("types") val types: List<FieldTypes>,
    @field:SerializedName("moves") val moves: List<FieldMoves>,
) {
    fun convertToDomain(): PokemonInfo = PokemonInfo(
        id = id,
        name = name,
        species = species.name,
        height = height,
        weight = weight,
        abilities = abilities.map { it.ability.name },
        types = types.map { it.type.name },

        hp = extractStatistics("hp"),
        attack = extractStatistics("attack"),
        defense = extractStatistics("defense"),
        spAttack = extractStatistics("special-attack"),
        spDefense = extractStatistics("special-defense"),
        speed = extractStatistics("speed")
    )
    fun convertToDatabase(): PokemonEntity = PokemonEntity(
        containsInfo = true,

        id = id,
        name = name,
        species = species.name,
        height = height,
        weight = weight,
        abilities = abilities.map { it.ability.name },
        types = types.map { it.type.name },

        hp = extractStatistics("hp"),
        attack = extractStatistics("attack"),
        defense = extractStatistics("defense"),
        spAttack = extractStatistics("special-attack"),
        spDefense = extractStatistics("special-defense"),
        speed = extractStatistics("speed")
    )

    private fun extractStatistics(query: String): Int {
        val field = stats.find { query == it.stat.name }
        return field?.base ?: 0
    }
}

data class FieldMoves(
    @field:SerializedName("move") val move: FieldItem,
)

data class FieldTypes(
    @field:SerializedName("slot") val slot: Int,
    @field:SerializedName("type") val type: FieldItem,
)

data class FieldStatistics(
    @field:SerializedName("base_stat") val base: Int,
    @field:SerializedName("effort") val effort: Int,
    @field:SerializedName("stat") val stat: FieldItem,
)

data class FieldIndex(
    @field:SerializedName("game_index") val index: Int,
    @field:SerializedName("version") val version: FieldItem
)

data class FieldAbility(
    @field:SerializedName("ability") val ability: FieldItem,
    @field:SerializedName("is_hidden") val hidden: Boolean,
    @field:SerializedName("slot") val slot: Int,
)

data class FieldItem(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url: String
)
