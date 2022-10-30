@file:Suppress("UNUSED_PARAMETER")

package no.nilsen.compose

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import no.nilsen.compose.compose.ComposeActivity
import no.nilsen.compose.databinding.DatabindingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startCompose(view: View) {
        startActivity(Intent(this, ComposeActivity::class.java))
    }

    fun startDatabinding(view: View) {
        startActivity(Intent(this, DatabindingActivity::class.java))
    }
}