package com.paf.cervezaskoin.data.database


import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.data.source.LocalDataSource
import com.paf.cervezaskoin.data.toRoomBeer
import com.paf.cervezaskoin.data.toBeer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: BeerDataBase) : LocalDataSource {

    private val beerDao = db.beerDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) { beerDao.beerCount() <= 0 }

    override suspend fun saveBeers(beers: List<Beer>) {
        withContext(Dispatchers.IO) { beerDao.insertBeers(beers.map { it.toRoomBeer() }) }
    }

    override suspend fun getBeers(): List<Beer> = withContext(Dispatchers.IO) {
        beerDao.getAll().map { it.toBeer() }
    }

    override suspend fun findById(id: Int) = withContext(Dispatchers.IO) {
        beerDao.findById(id).toBeer()
    }

    override suspend fun update(beer: Beer) {
        withContext(Dispatchers.IO) { beerDao.updateBeer( beer.toRoomBeer()) }
    }
}