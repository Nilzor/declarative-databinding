package no.nilsen.compose.databinding

import android.view.View
import android.view.ViewParent
import android.widget.ListView
import androidx.annotation.IdRes

object ListViewHelper {
    /**
     * Traverses the view hierarchy upwards until it finds view with id [listElementViewId],
     * then assumes a ListView is the parent of that, enabling us to find the viewModel bound
     * to that particular view.
     */
    fun <T> getViewModelFor(view: View, @IdRes listElementViewId: Int): T {
        var itemRoot: ViewParent = view.parent
        while ((itemRoot as? View)?.id != listElementViewId && itemRoot.parent != null) {
            itemRoot = itemRoot.parent
        }
        val listView = (itemRoot as View).parent as ListView
        val ix = listView.indexOfChild(itemRoot)
        return listView.adapter.getItem(ix) as T
    }
}