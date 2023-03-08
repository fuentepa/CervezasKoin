package com.paf.cervezaskoin.data.repository

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.data.source.LocalDataSource
import com.paf.cervezaskoin.data.source.RemoteDataSource
import kotlinx.coroutines.flow.*

class BeersRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun getBeers(): Flow<Either<Exception, List<Beer>>> = flow {
        try {
            if (localDataSource.isEmpty()) {
                remoteDataSource.getBeers(page = 1).let { beers ->
                    localDataSource.saveBeers(beers)
                }
                remoteDataSource.getBeers(page = 2).let { beers ->
                    localDataSource.saveBeers(beers)
                }
            }

            val beers = localDataSource.getBeers()
            emit(Right(beers))
        } catch (ex: Exception) {
            emit(Left(ex))
        }
    }

    suspend fun findById(id: Int): Beer = localDataSource.findById(id)

    suspend fun update(beer: Beer) = localDataSource.update(beer)
}