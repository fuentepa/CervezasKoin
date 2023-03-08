package com.paf.cervezaskoin.data

import com.paf.cervezaskoin.data.database.Beer as DataBaseBeer
import com.paf.cervezaskoin.data.entities.Beer as ServerBeer


fun ServerBeer.toDataBaseBeer(): DataBaseBeer =
    DataBaseBeer(
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

fun DataBaseBeer.toServerBeer(): ServerBeer =
    ServerBeer(
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
