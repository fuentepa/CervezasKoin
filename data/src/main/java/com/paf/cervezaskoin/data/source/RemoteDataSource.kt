package com.paf.cervezaskoin.data.source

import com.paf.cervezaskoin.data.entities.Beer

interface RemoteDataSource {
    suspend fun getBeers(page: Int = 1, per_page: Int = 50): List<Beer>
}