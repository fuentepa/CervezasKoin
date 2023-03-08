package com.paf.cervezaskoin.data.database

import androidx.room.*

@Dao
interface BeerDao {
    @Query("SELECT * FROM Beers")
    fun getAll(): List<Beer>

    @Query("SELECT * FROM Beers WHERE id = :id")
    fun findById(id: Int): Beer

    @Query("SELECT COUNT(id) FROM Beers")
    fun beerCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBeers(beers: List<Beer>)

    @Update
    fun updateBeer(beer: Beer)
}