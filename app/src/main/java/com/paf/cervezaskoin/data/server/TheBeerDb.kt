package com.paf.cervezaskoin.data.server

import com.paf.cervezaskoin.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheBeerDb(baseUrl: String) {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level =  if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    private val contentType = "application/json".toMediaType()

    private var json: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    val service: TheBeerDbService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        //.addConverterFactory(Json.asConverterFactory(contentType))
        // .addConverterFactory(json.asConverterFactory(contentType))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run { create(TheBeerDbService::class.java) }
}