package com.paf.cervezaskoin.data

import com.paf.cervezaskoin.data.database.RoomBeer
import com.paf.cervezaskoin.data.entities.Beer


fun Beer.toRoomBeer(): RoomBeer =
    RoomBeer(
        id,
        name,
        tagline,
        description,
        imageUrl,
        abu,
        ibu,
        foodPairing,
        available = true
    )

fun RoomBeer.toBeer(): Beer =
    Beer(
        id,
        name,
        tagline,
        description,
        imageUrl,
        abu,
        ibu,
        foodPairing,
        available
    )
