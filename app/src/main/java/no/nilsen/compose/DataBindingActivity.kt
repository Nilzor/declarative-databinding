package no.nilsen.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import no.nilsen.compose.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDataBindingBinding.inflate(layoutInflater)
        binding.viewModel = UserVersusDataChangeExample()
        setContentView(binding.root)

    }
}