package com.appifly.tvchannel.ui.admob

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.appifly.tvchannel.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdmobBanner(modifier :Modifier= Modifier) {
    AndroidView(modifier =modifier.fillMaxWidth(), factory = { context ->
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId=BuildConfig.BANNER_ADD_ID
            loadAd(AdRequest.Builder().build())
        }

    })
}