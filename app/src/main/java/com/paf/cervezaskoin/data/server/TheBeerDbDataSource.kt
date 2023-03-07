package com.paf.cervezaskoin.data.server

import arrow.core.Either
import com.paf.cervezaskoin.data.source.RemoteDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import com.paf.cervezaskoin.data.entities.Beer


class TheBeerDbDataSource(private val theBeerDb: TheBeerDb) : RemoteDataSource {

    override suspend fun getBeers(page: Int, per_page: Int): List<Beer> {
        return suspendCancellableCoroutine { cont ->
            theBeerDb.service.listBeersAsync(page, per_page)
                .enqueue(object : Callback<List<Beer>> {
                    override fun onResponse(
                        call: Call<List<Beer>>,
                        response: Response<List<Beer>>
                    ) {
                        if (response.isSuccessful)
                            response.body()?.let { body ->
                                cont.resume(body)
                            }
                        else
                            response.errorBody()?.let {
                                throw Exception(it.string())
                            }
                    }

                    override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                        t.message?.let { throw Exception(it) }
                    }
                })
        }
    }
}


