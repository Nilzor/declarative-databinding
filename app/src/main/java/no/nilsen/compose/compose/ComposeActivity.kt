package no.nilsen.compose.compose

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
import androidx.compose.ui.unit.sp
import no.nilsen.compose.common.ShoppingCartViewModel
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
        .padding(8.dp)
    ) {
        for (product in pageState.value.productList) {
            Row {
                Text(product.name,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .requiredWidth(110.dp)
                )
                Counter(
                    product.count,
                    product.enableDecrease,
                    { viewModel.increaseCounter(product) },
                    { viewModel.decreaseCounter(product) },
                )
           }
        }
        Text("Total count: ${pageState.value.totalProductCount}",
            fontSize = 22.sp)
    }
}

@Composable
fun Counter(value: Int,
            enableDecrease: Boolean,
            onIncreaseClick: () -> Unit,
            onDecreaseClick: () -> Unit,
) {
    Row {
        Button(
            onClick = onDecreaseClick,
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            enabled = enableDecrease,
        ) {
            Text(text = "-")
        }
        Text(
            text = value.toString(),
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(PaddingValues(horizontal = 8.dp)),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onIncreaseClick,
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
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

