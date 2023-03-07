package com.paf.cervezaskoin.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerialName("image_url") val imageUrl: String,
    val abu: Double,
    val ibu: Double,
    @SerialName("food_pairing") val foodPairing: List<String>,
    val available: Boolean
)
