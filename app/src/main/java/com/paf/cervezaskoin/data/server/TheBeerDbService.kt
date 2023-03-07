package com.paf.cervezaskoin.data.server

import com.paf.cervezaskoin.data.entities.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheBeerDbService {
    @GET("beers")
    fun listBeersAsync(
        @Query("page") page: Int?,
        @Query("per_page") per_page: Int = 50
    ): Call<List<Beer>>
}