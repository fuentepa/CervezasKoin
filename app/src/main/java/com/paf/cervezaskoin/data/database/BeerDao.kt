package com.paf.cervezaskoin.data.database

import androidx.room.*

@Dao
interface BeerDao {
    @Query("SELECT * FROM Beers")
    fun getAll(): List<RoomBeer>

    @Query("SELECT * FROM Beers WHERE id = :id")
    fun findById(id: Int): RoomBeer

    @Query("SELECT COUNT(id) FROM Beers")
    fun beerCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBeers(roomBeers: List<RoomBeer>)

    @Update
    fun updateBeer(roomBeer: RoomBeer)
}