package com.paf.cervezaskoin.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Beers")
data class RoomBeer(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String,
    val abu: Double,
    val ibu: Double,
    val foodPairing: List<String>,
    val available: Boolean
)
