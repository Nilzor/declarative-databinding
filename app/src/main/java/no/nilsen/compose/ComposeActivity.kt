package no.nilsen.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nilsen.compose.ui.theme.ComposeTestingTheme

class ComposeActivity : ComponentActivity() {
    val viewModel: CompViewModel by viewModels<CompViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
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
        Button(
            onClick = viewModel::decreaseCounter,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),
        ) {
            Text(text = "-")
        }
        Text(
            text = state.value.count.toString(),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(PaddingValues(all = 4.dp)),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = viewModel::increaseCounter,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),
        ) {
            Text(text = "+")
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

