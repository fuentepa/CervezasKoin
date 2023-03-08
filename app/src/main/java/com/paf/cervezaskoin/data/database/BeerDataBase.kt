package com.paf.cervezaskoin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Beer::class], version = 1)
@TypeConverters(RoomListStringProperty::class)
abstract class BeerDataBase: RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BeerDataBase::class.java,
            "beer-db"
        ).build()
    }

    abstract fun beerDao(): BeerDao
}