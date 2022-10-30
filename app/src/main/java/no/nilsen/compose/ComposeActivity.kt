package no.nilsen.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nilsen.compose.ui.theme.ComposeTestingTheme

class ComposeActivity : ComponentActivity() {
    val viewModel: ShoppingCartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Page(viewModel)
                }
            }
        }
    }
}


@Composable
fun Page(viewModel: ShoppingCartViewModel) {
    val pageState = viewModel.stateFlow.collectAsState()

    // Todo: Grid / LazyVerticalGrid

    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        for (product in pageState.value.productList) {
            Row {
                Text(product.name, modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                )
                Counter(
                    product.count,
                    { viewModel.increaseCounter(product) },
                    { viewModel.decreaseCounter(product) },
                )
           }
        }
        Text("Total count: ${pageState.value.totalProductCount}")
    }
}

@Composable
fun Counter(value: Int, onIncreaseClick: () -> Unit, onDecreaseClick: () -> Unit) {
    Row {
        Button(
            onClick = onDecreaseClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),
        ) {
            Text(text = "-")
        }
        Text(
            text = value.toString(),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(PaddingValues(all = 4.dp)),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onIncreaseClick,
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
            Page(ShoppingCartViewModel())
            //displayList()
        }
    }
}

