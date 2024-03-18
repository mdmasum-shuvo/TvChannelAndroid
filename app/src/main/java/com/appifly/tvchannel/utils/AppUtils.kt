package com.appifly.tvchannel.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
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
}