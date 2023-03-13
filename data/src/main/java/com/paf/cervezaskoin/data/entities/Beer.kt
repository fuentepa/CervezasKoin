package com.paf.cervezaskoin.data.entities

import com.google.gson.annotations.SerializedName


data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("image_url") val imageUrl: String,
    val abu: Double,
    val ibu: Double,
    @SerializedName("food_pairing") val foodPairing: List<String>,
    val available: Boolean
)
