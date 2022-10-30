package no.nilsen.compose.databinding


import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import no.nilsen.compose.BR
import no.nilsen.compose.R
import no.nilsen.compose.common.ShoppingCartViewModel

class ShoppingCartViewModelDatabinding : ShoppingCartViewModel() {


    val itemBinding: ItemBinding<ProductCounterState> =
        ItemBinding.of(BR.viewModel, R.layout.view_product)

    val observableModel = ObservableField<PageState?>()

    fun startObserving() {
        viewModelScope.launch {
            stateFlow.collect(observableModel::set)
        }
    }
}