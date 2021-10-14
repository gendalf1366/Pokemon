package ru.gendalf13666.pokemon.repository.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
@TypeConverters(FieldConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDAO

    companion object { const val DATABASE_NAME = "local_db" }
}
