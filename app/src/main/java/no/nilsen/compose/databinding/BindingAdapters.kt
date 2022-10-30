package no.nilsen.compose.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("app:textInt")
        fun intToText(textView: TextView, intVal: Int) {
            textView.setText(intVal.toString())
        }
    }
}