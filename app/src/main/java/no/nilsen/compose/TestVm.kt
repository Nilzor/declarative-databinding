package no.nilsen.compose

import android.text.Editable
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class TestVm : ViewModel() {

    val text = ObservableField<String>()

    fun onTextChange(text: String) {
        val x: EditText
        Log.i("zzz", "onTextChanged:" + text)
    }

    fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.i("zzz", "beforeTextChanged:" + text)
    }

    fun afterTextChanged(editable: Editable) {
        Log.i("zzz", "after change: $editable")
    }

}