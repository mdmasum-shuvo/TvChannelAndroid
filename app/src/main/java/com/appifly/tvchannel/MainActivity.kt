package com.appifly.tvchannel

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.MainViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.tv_ui.TvHomeScreen
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var appUpdateManager: AppUpdateManager? = null
    private val TAG: String = MainActivity::class.java.simpleName
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen()
        }
        appUpdateManager = AppUpdateManagerFactory.create(this)
        checkUpdate()

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
) {
    val navController = rememberNavController()
    hiltViewModel<MainViewModel>()
    val channelViewModel: ChannelViewModel = hiltViewModel()
    Scaffold(bottomBar = {

    }) { paddingValues ->
        Column(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            NavHost(
                navController = navController,
                startDestination = Routing.HomeScreen.routeName
            ) {



                composable(Routing.HomeScreen.routeName) {

                    TvHomeScreen(navController = navController, channelViewModel = channelViewModel)
                }



            }
        }

    }

}




