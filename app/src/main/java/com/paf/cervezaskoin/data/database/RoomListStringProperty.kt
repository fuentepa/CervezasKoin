package com.paf.cervezaskoin.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.*


class RoomListStringProperty {

    @TypeConverter
    fun stringToList(data: String?): List<String> {
        return data?.let {
            Json.decodeFromString<List<String>>(it)
        } ?: Collections.emptyList()
    }

    @TypeConverter
    fun ListToString(lista: List<String?>?): String {
        return Json.encodeToString(lista)
    }
}