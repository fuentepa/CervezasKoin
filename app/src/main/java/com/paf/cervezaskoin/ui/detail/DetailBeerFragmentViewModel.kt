package com.paf.cervezaskoin.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paf.cervezaskoin.data.entities.Beer
import com.paf.cervezaskoin.domain.FindByIdUseCase
import com.paf.cervezaskoin.ui.common.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailBeerFragmentViewModel(
    private val beerId: Int,
    private val findByIdUseCase: FindByIdUseCase,
  //  private val toggleAvailableUseCase: ToggleAvailableUseCase
):  ViewModel()  {

    private val _status =  MutableStateFlow<UIState<Beer>>(UIState.EMPTY)
    val status = _status.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        findById(beerId)
    }

    fun findById(beerId: Int) {
        viewModelScope.launch {
            _status.emit(UIState.LOADING)
            withContext(Dispatchers.IO) {
                _status.emit(UIState.SUCCESS(findByIdUseCase(beerId)))
            }
        }
    }

}