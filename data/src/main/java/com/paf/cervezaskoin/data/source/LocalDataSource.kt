package com.paf.cervezaskoin.data.source

import com.paf.cervezaskoin.data.entities.Beer

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveBeers(beers: List<Beer>)
    suspend fun getBeers(): List<Beer>
    suspend fun findById(id: Int): Beer
    suspend fun update(beer: Beer)
}