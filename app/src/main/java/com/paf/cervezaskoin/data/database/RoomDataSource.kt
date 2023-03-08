package com.paf.cervezaskoin.data.database


import com.paf.cervezaskoin.data.source.LocalDataSource
import com.paf.cervezaskoin.data.toDataBaseBeer
import com.paf.cervezaskoin.data.toServerBeer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.paf.cervezaskoin.data.entities.Beer as ServerBeer

class RoomDataSource(db: BeerDataBase) : LocalDataSource {

    private val beerDao = db.beerDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) { beerDao.beerCount() <= 0 }

    override suspend fun saveBeers(beers: List<ServerBeer>) {
        withContext(Dispatchers.IO) { beerDao.insertBeers(beers.map { it.toDataBaseBeer() }) }
    }

    override suspend fun getBeers(): List<ServerBeer> = withContext(Dispatchers.IO) {
        beerDao.getAll().map { it.toServerBeer() }
    }

    override suspend fun findById(id: Int) = withContext(Dispatchers.IO) {
        beerDao.findById(id).toServerBeer()
    }

    override suspend fun update(beer: ServerBeer) {
        withContext(Dispatchers.IO) { beerDao.updateBeer( beer.toDataBaseBeer()) }
    }
}