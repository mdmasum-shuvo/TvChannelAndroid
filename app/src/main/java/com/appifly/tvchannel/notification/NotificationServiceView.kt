package com.appifly.tvchannel.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import coil.ImageLoader
import coil.request.ImageRequest
import com.appifly.tvchannel.MainActivity
import com.appifly.tvchannel.R
import kotlin.random.Random


class NotificationServiceView(
    private val context: Context, private val title: String,
    private val message: String, private val imageUrl: Uri?
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification() {
        val notification =
            NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showExpandableNotification() {

        val notification =
            NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .setStyle(
                    NotificationCompat
                        .BigPictureStyle()
                        .bigPicture(
                            context.bitmapFromResource(
                                R.mipmap.ic_launcher
                            )
                        )
                )
                .build()
        notificationManager.notify(Random.nextInt(), notification)
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}