package no.nilsen.compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ShoppingCartViewModel : ViewModel() {
    private var currentState: PageState = PageState(
        productList = listOf(
            ProductCounterState("Voksen", 0),
            ProductCounterState("Barn", 0),
            ProductCounterState("Honnør", 0),
        ),
        totalProductCount = 0
    )

    val stateFlow = MutableStateFlow(currentState)

    data class PageState(
        val productList: List<ProductCounterState>,
        val totalProductCount: Int = 0,
    )

    data class ProductCounterState(val name: String, val count: Int)

    fun increaseCounter(state: ProductCounterState) {
        modifyCount(state, +1)
    }

    fun decreaseCounter(state: ProductCounterState) {
        if (state.count > 0) {
            modifyCount(state, -1)
        }
    }

    private fun modifyCount(state: ProductCounterState, delta: Int) {
        val newProductList = currentState.productList.map {
            if (it == state) it.copy(count = it.count + delta)
            else it
        }
        setState(
            PageState(
                productList = newProductList,
                totalProductCount = currentState.totalProductCount + delta
            )
        )
    }

    private fun setState(newState: PageState) {
        currentState = newState
        stateFlow.value = newState
    }
}