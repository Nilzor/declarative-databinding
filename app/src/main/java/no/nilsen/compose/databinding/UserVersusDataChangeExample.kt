package no.nilsen.compose.databinding

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Illustrating that we get an afterTextChanged both when data is set
 * by user and when set by computer. Can not distinguish in afterTextChanged.
 * May or may not be an issue compared to Compose.
 */
class UserVersusDataChangeExample : ViewModel() {

    val text = ObservableField<String>()


    fun afterTextChanged(editable: Editable) {
        Log.i("zzz", "after change: $editable")
    }

    fun setUuid(view: View) {
        Log.i("zzz", "setting new text")
        text.set(UUID.randomUUID().toString())
    }
}