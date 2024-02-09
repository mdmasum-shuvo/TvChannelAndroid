package com.appifly.tvchannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.tvchannel.ui.theme.TvChannelTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvChannelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }

        }
    }
}

@Composable
fun Greeting(viewModel: CategoryViewModel= hiltViewModel()) {
    Text(
        text = "Hello !",
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TvChannelTheme {
    }
}