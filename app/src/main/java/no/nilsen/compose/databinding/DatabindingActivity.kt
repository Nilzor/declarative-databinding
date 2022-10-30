package no.nilsen.compose.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DatabindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDataBindingBinding.inflate(layoutInflater)
        binding.viewModel = ShoppingCartViewModelDatabinding()
        setContentView(binding.root)
    }
}