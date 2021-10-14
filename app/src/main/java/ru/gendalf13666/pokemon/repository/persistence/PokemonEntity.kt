package ru.gendalf13666.pokemon.repository.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.gendalf13666.pokemon.repository.models.Pokemon
import ru.gendalf13666.pokemon.repository.models.PokemonInfo

@Entity(tableName = "pokemons")
data class PokemonEntity(
    val containsInfo: Boolean = false,

    val id: Int,
    @PrimaryKey val name: String,
    val species: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val abilities: List<String>? = null,
    val types: List<String>? = null,

    val hp: Int? = null,
    val attack: Int? = null,
    val defense: Int? = null,
    val spDefense: Int? = null,
    val spAttack: Int? = null,
    val speed: Int? = null,
) {
    fun convertToDomainPokemon(): Pokemon = Pokemon(name = name, id = id)
    fun convertToDomainInfoModel(): PokemonInfo = PokemonInfo(
        id = id,
        name = name,
        species = species ?: "",
        height = height ?: 0,
        weight = weight ?: 0,
        abilities = abilities ?: emptyList(),
        types = types ?: emptyList(),
        hp = hp ?: 0,
        attack = attack ?: 0,
        defense = defense ?: 0,
        spDefense = spDefense ?: 0,
        spAttack = spAttack ?: 0,
        speed = speed ?: 0
    )
}

/**
 * Extension function to convert database layer to domain data
 */
fun List<PokemonEntity>.convertToDomainPokemon(): List<Pokemon> = map { it.convertToDomainPokemon() }

/**
 * Data type conversion to save Json to the database
 */
class FieldConverter {
    @TypeConverter
    fun readFromField(data: List<String?>?): String? {
        if (data == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String?>?>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun writeToField(dataString: String?): List<String>? {
        if (dataString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String>>(dataString, type)
    }
}
