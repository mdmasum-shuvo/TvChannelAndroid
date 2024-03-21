package com.appifly.tvchannel.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.appifly.tvchannel.R

object AppUtils {

    fun shareApp(context: Context) {
        try {
            val appPackageName = context.packageName
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                context.resources.getString(R.string.share_app_dec) + Constants.GOOGLE_PLAY_URL + appPackageName
            )
            sendIntent.type = "text/plain"
            context.startActivity(sendIntent)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun rateThisApp(context: Context) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse( Constants.GOOGLE_PLAY_URL+ context.packageName)
                )
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

     fun hideSystemUI(activity: Activity) {
        // Configure the behavior of the hidden system bars
        val windowInsetsController = WindowCompat.getInsetsController(activity.window, activity.window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}