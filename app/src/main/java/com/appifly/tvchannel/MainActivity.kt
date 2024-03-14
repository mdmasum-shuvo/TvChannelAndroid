package com.appifly.tvchannel

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.app_data_source.viewmodel.MainViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.bottom_nav.BottomNavigation
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.view.category.CategoryScreen
import com.appifly.tvchannel.ui.view.channel_screen.ChannelScreen
import com.appifly.tvchannel.ui.view.favorite.FavoriteChannelListScreen
import com.appifly.tvchannel.ui.view.favorite.FavoriteScreen
import com.appifly.tvchannel.ui.view.home.HomeScreen
import com.appifly.tvchannel.ui.view.menu.MenuScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

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
private fun MainScreenView(

) {
    val navController = rememberNavController()
    hiltViewModel<MainViewModel>()
    val showBottomNav = remember { mutableStateOf(false) }

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val channelViewModel: ChannelViewModel = hiltViewModel()
    val homeViewModel:HomeViewModel= hiltViewModel()
    Scaffold(bottomBar = {if (showBottomNav.value) BottomNavigation(navController,homeViewModel) }) { paddingValues ->
        Column(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            NavHost(
                navController = navController,
                startDestination = Routing.HomeScreen.routeName
            ) {
                // Auth
                composable(Routing.HomeScreen.routeName) {
                    showBottomNav.value = true

                    HomeScreen(categoryViewModel,channelViewModel,homeViewModel)
                }

                composable(Routing.MenuScreen.routeName) {
                    showBottomNav.value = true

                    MenuScreen()
                }

                composable(Routing.ChannelScreen.routeName) {
                    showBottomNav.value = true

                    ChannelScreen(categoryViewModel,channelViewModel)
                }

                composable(Routing.FavoriteScreen.routeName) {
                    showBottomNav.value = true

                    FavoriteScreen(navController,categoryViewModel,channelViewModel)
                }
                composable(Routing.FavoriteChannelListScreen.routeName) {
                    showBottomNav.value = false

                    FavoriteChannelListScreen(channelViewModel)
                }
            }
        }

    }

}



