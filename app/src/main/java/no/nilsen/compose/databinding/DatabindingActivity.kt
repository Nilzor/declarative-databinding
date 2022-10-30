package no.nilsen.compose.databinding

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import no.nilsen.compose.R
import no.nilsen.compose.common.ShoppingCartViewModel.ProductCounterState

class DatabindingActivity : AppCompatActivity() {
    val viewModel: ShoppingCartViewModelDatabinding by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDataBindingBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        viewModel.startObserving()
        setContentView(binding.root)
    }

    fun increaseCounter(view: View) {
        val clickedViewModel = ListViewHelper.getViewModelFor<ProductCounterState>(view, R.id.product_item_root)
        viewModel.increaseCounter(clickedViewModel)
    }

    fun decreaseCounter(view: View) {
        val clickedViewModel = ListViewHelper.getViewModelFor<ProductCounterState>(view, R.id.product_item_root)
        viewModel.decreaseCounter(clickedViewModel)
    }
}