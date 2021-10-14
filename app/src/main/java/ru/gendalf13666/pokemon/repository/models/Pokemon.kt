package ru.gendalf13666.pokemon.repository.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val id: Int
) : Parcelable {
    fun getImageUrl() = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
}
