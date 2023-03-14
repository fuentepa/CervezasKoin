package com.paf.cervezaskoin.ui.common

sealed class UIState<out T> {
    object LOADING: UIState<Nothing>()
    data class SUCCESS<T>(val data: T): UIState<T>()
    data class ERROR(val errorMessage: String): UIState<Nothing>()
    object EMPTY: UIState<Nothing>()
}