package no.nilsen.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch

class CompViewModel : ViewModel() {
    var currentState = UiState(1)
    fun increaseCounter() {
        viewModelScope.launch {
            val newState = UiState(currentState.count + 1)
            currentState = newState
            stateFlow.tryEmit(newState)
        }
    }

    val stateFlow = MutableStateFlow(currentState)

    data class UiState(val count: Int)
}