package no.nilsen.compose.databinding

import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.ListView
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
        val clickedViewModel = getViewModelFor(view)
        viewModel.increaseCounter(clickedViewModel)
    }

    fun decreaseCounter(view: View) {
        val clickedViewModel = getViewModelFor(view)
        viewModel.decreaseCounter(clickedViewModel)
    }

    private fun getViewModelFor(view: View): ProductCounterState {
        // Not pretty code.. better solution wanted
        var itemRoot: ViewParent = view.parent
        while ((itemRoot as? View)?.id != R.id.product_item_root) {
            itemRoot = itemRoot.parent
        }
        val listView = (itemRoot as View).parent as ListView
        val ix = listView.indexOfChild(itemRoot)
        return listView.adapter.getItem(ix) as ProductCounterState
    }
}