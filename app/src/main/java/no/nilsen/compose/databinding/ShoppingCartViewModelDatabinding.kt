package no.nilsen.compose.databinding

import me.tatarka.bindingcollectionadapter2.ItemBinding
import no.nilsen.compose.BR
import no.nilsen.compose.R
import no.nilsen.compose.common.ShoppingCartViewModel

class ShoppingCartViewModelDatabinding : ShoppingCartViewModel() {
    val itemBinding: ItemBinding<ProductCounterState> =
        ItemBinding.of(BR.viewModel, R.layout.view_product)
}