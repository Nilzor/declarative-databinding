package no.nilsen.compose.databinding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("app:textInt")
        fun intToText(textView: TextView, intVal: Int) {
            textView.setText(intVal.toString())
        }

        /**
         * Bind to function without input parameters for a click listener.
         * Stops you from getting those pesky "unused variable" warnings all over
         * the place when "view: View" is unused.
         */
        @JvmStatic
        @BindingAdapter("onClick")
        fun onClick(view: View, action: (() -> Unit)?) {
            view.setOnClickListener {
                action?.invoke()
            }
        }
    }
}