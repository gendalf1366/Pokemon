package ru.gendalf13666.pokemon.repository.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonInfo(
    val id: Int,
    val name: String,
    val species: String,
    val height: Int,
    val weight: Int,
    val abilities: List<String>,
    val types: List<String>,

    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spDefense: Int,
    val spAttack: Int,
    val speed: Int,
) : Parcelable {
    fun getNumberString() = String.format("#%03d", id)
    fun getWeightString() = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString() = String.format("%.1f M", height.toFloat() / 10)
    fun getImageUrl() = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
}
