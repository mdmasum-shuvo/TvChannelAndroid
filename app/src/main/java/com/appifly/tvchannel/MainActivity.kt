package com.appifly.tvchannel

import android.app.Activity
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint

private var mInterstitialAd: InterstitialAd? = null

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var appUpdateManager: AppUpdateManager? = null

    private val listener: InstallStateUpdatedListener =
        InstallStateUpdatedListener { installState ->
            if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                // After the update is downloaded, show a notification
                // and request user confirmation to restart the app.
                Log.d(ContentValues.TAG, "An update has been downloaded")
                Toast.makeText(this, "An update has been downloaded", Toast.LENGTH_SHORT).show()
                showSnackBarForCompleteUpdate()
            }
        }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val activityResultLauncherForAppUpdate = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result: ActivityResult ->
        // handle callback
        if (result.resultCode != RESULT_OK) {
            Log.d(ContentValues.TAG, "Update flow failed! Result code: " + result.resultCode)
            // If the update is canceled or fails, request to start the update again.
            checkUpdate()
        }
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        appUpdateManager = AppUpdateManagerFactory.create(this)

        checkUpdate()
        setContent {
            TvChannelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    loadInterstitialAdd(this)
                    MainScreenView(activity = this)
                }
            }

        }
        // Set up an OnPreDrawListener to the root view.
    }

    override fun onStop() {
        appUpdateManager?.unregisterListener(listener)
        super.onStop()
    }

    private fun showSnackBarForCompleteUpdate() {
        appUpdateManager?.completeUpdate()
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkUpdate() {
        // Returns an intent object that you use to check for an update.
        // Won't call for dev flavour as the playstore application id is different
        if (BuildConfig.DEBUG) {
            return
        }
        appUpdateManager?.registerListener(listener)
        val appUpdateInfoTask = appUpdateManager?.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        Log.d(ContentValues.TAG, "Checking for updates")
        appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                // Request the update.
                Log.d(ContentValues.TAG, "Update available")
                appUpdateManager?.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // an activity result launcher registered via registerForActivityResult
                    activityResultLauncherForAppUpdate,
                    // Or pass 'AppUpdateType.FLEXIBLE' to newBuilder() for flexible updates.
                    AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()
                )
            } else {
                Log.d(ContentValues.TAG, "No Update available")
            }
        }
    }
}

/**
 * @Composable fun for start destination and navigation to screen
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MainScreenView(
    activity: Activity
) {
    val navController = rememberNavController()
    hiltViewModel<MainViewModel>()
    val showBottomNav = remember { mutableStateOf(false) }

    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val channelViewModel: ChannelViewModel = hiltViewModel()
    val homeViewModel: HomeViewModel = hiltViewModel()
    Scaffold(bottomBar = {
        if (showBottomNav.value) BottomNavigation(
            navController,
            homeViewModel
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            NavHost(
                navController = navController,
                startDestination = Routing.HomeScreen.routeName
            ) {


                composable(Routing.MenuScreen.routeName) {
                    showBottomNav.value = true

                    MenuScreen()
                }

                composable(Routing.HomeScreen.routeName) {
                    showBottomNav.value = true

                    HomeScreen(navController=navController, viewModel = categoryViewModel,channelViewModel=channelViewModel,homeViewModel= homeViewModel)
                }

                composable(Routing.ChannelScreen.routeName) {
                    showBottomNav.value = true

                    ChannelScreen(categoryViewModel, channelViewModel, navController)
                }

                composable(Routing.FavoriteScreen.routeName) {
                    showBottomNav.value = true
                    FavoriteScreen(navController, categoryViewModel, channelViewModel)
                }
                composable(Routing.FavoriteChannelListScreen.routeName) {
                    showBottomNav.value = false

                    FavoriteChannelListScreen(channelViewModel, navController)
                }
                composable(Routing.ChannelDetailScreen.routeName) {
                    showBottomNav.value = false
                    mInterstitialAd?.show(activity)
                    ChannelDetailScreen(
                        categoryViewModel,
                        channelViewModel,
                        navController = navController
                    )
                }
            }
        }

    }

}


private fun loadInterstitialAdd(activity: Activity) {
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


