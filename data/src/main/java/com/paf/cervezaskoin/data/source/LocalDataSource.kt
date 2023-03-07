package com.paf.cervezaskoin.data.source

import arrow.core.Either
import com.paf.cervezaskoin.data.entities.Beer
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveBeers(beers: List<Beer>)
    suspend fun getBeers(): List<Beer>
    suspend fun findById(id: Int): Flow<Either<Exception, Beer>>
    suspend fun update(beer: Beer)
}