package mostafa.roqay.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import viewmodel.HomeUiState
import viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: HomeViewModel = koinViewModel()
            Preview(viewModel)

        }
    }
}

@Composable
fun Preview(
    viewModel: HomeViewModel,
) {
    val homeState by viewModel.homeState.collectAsState()
    when (homeState) {
        is HomeUiState.Error -> {
            Text(text = "Error Occurred ${(homeState as HomeUiState.Error).exceptionMessage} ")
        }

        HomeUiState.Loading -> {
            Text(text = "Loading")
        }

        is HomeUiState.Success -> {
            Text("${(homeState as HomeUiState.Success).data.get(0)?.image}")
            AsyncImage(
                model = (homeState as HomeUiState.Success).data[0]?.image,
                contentDescription = null,
                modifier = Modifier
                    .height(220.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds
            )
        }

        HomeUiState.Uninitialized -> {
            Text(text = "Error Occurred in initialization")
        }
    }
}