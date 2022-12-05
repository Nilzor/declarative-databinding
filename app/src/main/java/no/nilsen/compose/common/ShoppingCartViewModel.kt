package no.nilsen.compose.common

open class ShoppingCartViewModel : HistoryViewModel<ShoppingCartViewModel.PageState>() {
    override val initialState: PageState = ShoppingCartStartState.state

    data class PageState(
        val productList: List<ProductCounterState>,
        val totalProductCount: Int = 0,
    )

    data class ProductCounterState(
        val name: String,
        val count: Int,
        val enableDecrease: Boolean = false,
    )

    fun increaseCounter(state: ProductCounterState) {
        modifyCount(state, +1)
    }

    fun decreaseCounter(state: ProductCounterState) {
        if (state.count > 0) {
            modifyCount(state, -1)
        }
    }

    private fun modifyCount(state: ProductCounterState, delta: Int) {
        val curState = stateFlow.value
        val newProductList = curState.productList.map {
            if (it == state) {
                val newCount = it.count + delta
                it.copy(count = newCount, enableDecrease = newCount > 0)
            }
            else it
        }
        setState(
            PageState(
                productList = newProductList,
                totalProductCount = curState.totalProductCount + delta
            )
        )
    }
}