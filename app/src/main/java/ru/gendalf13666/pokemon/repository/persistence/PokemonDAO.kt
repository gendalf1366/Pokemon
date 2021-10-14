package ru.gendalf13666.pokemon.repository.persistence

import androidx.room.*

@Dao
interface PokemonDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemon: List<PokemonEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemon: PokemonEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg pokemon: PokemonEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemon: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)

    @Delete
    fun delete(vararg pokemons: PokemonEntity)

    @Delete
    fun delete(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE name = :name LIMIT 1")
    fun findByName(name: String): PokemonEntity?
}
