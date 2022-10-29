package no.nilsen.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import no.nilsen.compose.ui.theme.ComposeTestingTheme

class ComposeActivity : ComponentActivity() {
    val viewModel: CompViewModel by viewModels<CompViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Counter(viewModel)
                }
            }
        }
    }
}

@Composable
fun Counter(viewModel: CompViewModel) {
    val state = viewModel.stateFlow.collectAsState()
    Row {
        Text("Count: ${state.value.count}")
        Button(onClick = viewModel::increaseCounter
        , colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
            Text(text = "Inc")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyContent() {
    ComposeTestingTheme {
        Surface( color = MaterialTheme.colors.background) {
            Counter(CompViewModel())
        }
    }
}

