package com.appifly.tvchannel.ui.admob

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.appifly.tvchannel.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = BuildConfig.BANNER_ADD_ID
            loadAd(AdRequest.Builder().build())
        }

    })
}

@Composable
fun AdmobBannerAdaptive(modifier: Modifier = Modifier) {

// Step 2: Create banner using activity context and set the inline ad size and
// ad unit ID.


    AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
        AdView(context).apply {
            val adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context, 320)

            adUnitId = BuildConfig.BANNER_ADD_ID
            setAdSize(adSize)
            loadAd(AdRequest.Builder().build())
            val adRequest = AdRequest.Builder().build()
            loadAd(adRequest)
        }

    })


// Step 3: Load an ad.


}