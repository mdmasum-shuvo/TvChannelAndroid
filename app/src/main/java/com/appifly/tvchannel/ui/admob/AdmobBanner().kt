package com.appifly.tvchannel.ui.admob

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    Column {
        AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = BuildConfig.BANNER_ADD_ID
                loadAd(AdRequest.Builder().build())
            }

        })
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AdmobBannerAdaptive(modifier: Modifier = Modifier) {

    Column {
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
        Spacer(modifier = Modifier.height(16.dp))

    }


// Step 3: Load an ad.


}