package no.nilsen.compose.common

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class HistoryViewModel<T> : ViewModel() {
    abstract val initialState: T
    var history: MutableList<T> = mutableListOf()  // todo use snapshotFlow?
    var index: Int = 0

    val stateFlow by lazy {
        history.add(initialState)
        MutableStateFlow<T>(initialState)
    }

    protected fun setState(newState: T) {
        stateFlow.value = newState
        history = (history.take(index + 1) + newState).toMutableList()
        index++
        updateBackForward()
    }

    fun updateBackForward() {
        canGoForward.set(index < history.size - 1)
        canGoBack.set(index > 0)
    }

    val canGoForward = ObservableBoolean()
    val canGoBack = ObservableBoolean()

    fun goForward() {
        if (!canGoForward.get()) return
        stateFlow.value = history[++index]
        updateBackForward()
    }

    fun goBack() {
        if (!canGoBack.get()) return
        stateFlow.value = history[--index]
        updateBackForward()
    }
}