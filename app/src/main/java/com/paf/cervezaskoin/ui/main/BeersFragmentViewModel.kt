package com.paf.cervezaskoin.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.domain.GetBeersUseCase
import com.paf.cervezaskoin.ui.common.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BeersFragmentViewModel(
    private val getBeersUseCase: GetBeersUseCase
): ViewModel() {

    //private val _beers = MutableStateFlow<List<Beer>>(emptyList())
    //val beers = _beers.asStateFlow()

    private val _status =  MutableStateFlow<State<List<Beer>>>(State.EMPTY)
    val status = _status.asStateFlow()

    var errorMessage: String? = null

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        getBeers()
    }

    fun getBeers(){
        viewModelScope.launch {
            _status.emit(State.LOADING)
            withContext(Dispatchers.IO) {
                getBeersUseCase().last().fold( {
                    it.message?.let { message ->
                        Log.e("getBeers()", message)
                        _status.emit(State.ERROR(message))
                    }
                }) {
                    _status.emit(State.SUCCESS(it))
                }
            } //PAF: no envio nada realmente porque quiero que me traiga dos paginas de 50
        }
    }
}