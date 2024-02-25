package com.appifly.tvchannel

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.category.CategoryScreen
import com.appifly.tvchannel.ui.view.channel_screen.ChannelScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        setContent {
            TvChannelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }

        }
        // Set up an OnPreDrawListener to the root view.
    }

}

/**
 * @Composable fun for start destination and navigation to screen
 */
@Composable
private fun MainScreenView() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routing.CategoryScreen.routeName
    ) {
        // Auth
        composable(Routing.CategoryScreen.routeName) {
            CategoryScreen(navController = navController)
        }

        composable(Routing.ChannelScreen.routeName) {
            ChannelScreen(navController = navController)
        }
    }
}



