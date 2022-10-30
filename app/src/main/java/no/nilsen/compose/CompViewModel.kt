package no.nilsen.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CompViewModel : ViewModel() {
    var currentState = UiState(1)
    val stateFlow = MutableStateFlow(currentState)
    data class UiState(val count: Int)

    fun increaseCounter() {
        setState(UiState(currentState.count + 1))
    }

    fun decreaseCounter() {
        if (currentState.count > 0) {
            setState(UiState(currentState.count - 1))
        }
    }

    private fun setState(newState: UiState) {
        currentState = newState
        stateFlow.value = newState
    }
}