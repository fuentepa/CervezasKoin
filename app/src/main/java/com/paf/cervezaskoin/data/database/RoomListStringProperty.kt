package com.paf.cervezaskoin.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.util.*


class RoomListStringProperty {

    @TypeConverter
    fun stringToList(data: String?): List<String> {
        if (data == null) {
            return Collections.emptyList()
        }
        return Json.decodeFromString<List<String>>(data)
    }

    @TypeConverter
    fun ListToString(lista: List<String?>?): String {
        return Json.encodeToString(lista)
    }
}