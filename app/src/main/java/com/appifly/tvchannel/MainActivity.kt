package com.appifly.tvchannel

import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
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
import com.appifly.tvchannel.ui.view.channel_screen.ChannelDetailScreen
import com.appifly.tvchannel.ui.view.channel_screen.ChannelScreen
import com.appifly.tvchannel.ui.view.favorite.FavoriteChannelListScreen
import com.appifly.tvchannel.ui.view.favorite.FavoriteScreen
import com.appifly.tvchannel.ui.view.home.HomeScreen
import com.appifly.tvchannel.ui.view.menu.MenuScreen
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint

private var mInterstitialAd: InterstitialAd? = null

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


                composable(Routing.MenuScreen.routeName) {
                    showBottomNav.value = true

                    MenuScreen()
                }

                composable(Routing.ChannelScreen.routeName) {
                    showBottomNav.value = true

                    ChannelScreen(categoryViewModel,channelViewModel,navController)
                }

                composable(Routing.FavoriteScreen.routeName) {
                    showBottomNav.value = true
                    FavoriteScreen(navController,categoryViewModel,channelViewModel)
                }
                composable(Routing.FavoriteChannelListScreen.routeName) {
                    showBottomNav.value = false

                    FavoriteChannelListScreen(channelViewModel,navController)
                }
                composable(Routing.ChannelDetailScreen.routeName) {
                    showBottomNav.value = false

                    ChannelDetailScreen(categoryViewModel,channelViewModel, navController = navController)
                }
            }
        }

    }

}



private fun loadInterstitialAdd(activity: Activity,) {
    val adRequest = AdRequest.Builder().build()

    InterstitialAd.load(
        activity,
        BuildConfig.INTERSTITIAL_ADD_ID,
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(ContentValues.TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(ContentValues.TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                //mInterstitialAd!!.show(this@MainActivity)
            }
        })
    mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdClicked() {
            // Called when a click is recorded for an ad.
            Log.d(ContentValues.TAG, "Ad was clicked.")
        }

        override fun onAdDismissedFullScreenContent() {
            // Called when ad is dismissed.
            Log.d(ContentValues.TAG, "Ad dismissed fullscreen content.")
            mInterstitialAd = null
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            // Called when ad fails to show.
            Log.e(ContentValues.TAG, "Ad failed to show fullscreen content.")
            mInterstitialAd = null
        }

        override fun onAdImpression() {
            // Called when an impression is recorded for an ad.
            Log.d(ContentValues.TAG, "Ad recorded an impression.")
        }

        override fun onAdShowedFullScreenContent() {
            // Called when ad is shown.
            Log.d(ContentValues.TAG, "Ad showed fullscreen content.")
        }
    }

}


