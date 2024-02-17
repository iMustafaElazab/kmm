package mostafa.roqay.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Preview()
        }
    }
}

@Composable
fun Preview() {
    val viewModel: HomeViewModel = koinViewModel()
    val homeState by viewModel.homeUiState.collectAsState()

    // Trigger data fetching explicitly
    LaunchedEffect(Unit) {
        viewModel.getBanners()
    }

    println("STATE:=>${homeState?.banners}")
}