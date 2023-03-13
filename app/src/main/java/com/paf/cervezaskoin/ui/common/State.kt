package com.paf.cervezaskoin.ui.common

import arrow.core.Either

sealed class State<out T> {
    object LOADING: State<Nothing>()
    data class SUCCESS<T>(val data: T): State<T>()
    data class ERROR(val errorMessage: String): State<Nothing>()
    object EMPTY: State<Nothing>()
}