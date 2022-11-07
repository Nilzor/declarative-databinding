package no.nilsen.compose.common

object ShoppingCartStartState {
    val state  = ShoppingCartViewModel.PageState(
        productList = listOf(
            ShoppingCartViewModel.ProductCounterState("Adult", 0),
            ShoppingCartViewModel.ProductCounterState("Child", 0),
            ShoppingCartViewModel.ProductCounterState("Bicycle", 0),
        ),
        totalProductCount = 0
    )
}