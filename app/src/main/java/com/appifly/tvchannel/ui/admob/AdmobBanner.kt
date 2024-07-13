package com.appifly.tvchannel.ui.admob

import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LiveData
import com.appifly.app_data_source.dto.AdIdDto
import com.appifly.tvchannel.BuildConfig
import com.appifly.tvchannel.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

private var adView: com.facebook.ads.AdView? = null


@Composable
fun AdmobBanner(
    modifier: Modifier = Modifier,
    adLiveData: LiveData<AdIdDto>?,
    isAdaptive: Boolean = false
) {
    adLiveData?.observeAsState()?.value?.let { adData ->
        val shouldShowResult = remember {
            mutableStateOf(false)
        }
        Column {
            AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
                AdView(context).apply {
                    when(isAdaptive){
                        true-> {
                            val adSize =
                                AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context, 320)
                            setAdSize(adSize)
                        }
                        false->{
                            setAdSize(AdSize.BANNER)
                        }
                    }
                    adUnitId =
                        if (BuildConfig.DEBUG) BuildConfig.BANNER_ADD_ID else adData.admobBanner.toString()
                    loadAd(AdRequest.Builder().build())
                }
            },
                update = {
                    it.apply {
                        loadAd(AdRequest.Builder().build())
                    }
                    it.adListener = object : AdListener() {
                        override fun onAdClicked() {
                            Log.d("TAG", "onAdClicked: ")
                        }

                        override fun onAdClosed() {
                            Log.d("TAG", "onAdClosed: ")
                        }


                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            shouldShowResult.value = true
                            adView?.visibility = android.view.View.VISIBLE
                        }

                        override fun onAdImpression() {
                            Log.d("TAG", "onAdImpression: ")
                        }

                        override fun onAdLoaded() {
                            Log.d("TAG", "onAdLoaded: ")
                        }

                        override fun onAdOpened() {

                            Log.d("TAG", "onAdOpened: ")
                        }
                    }
                })
        }
        if (shouldShowResult.value) {
            FacebookBannerAdsView(if (BuildConfig.DEBUG) BuildConfig.FB_BANNER_ADD_ID else adData.fbBanner.toString())
        }
    }
}


@Composable
fun FacebookBannerAdsView(bannerId: String) {
    AndroidView(
        factory = { context ->
            // Create and configure your Android View here
            val view = LayoutInflater.from(context).inflate(R.layout.facebook_ads, null, false)
            val bannerContainer = view.findViewById<LinearLayout>(R.id.banner_container)
            adView =
                com.facebook.ads.AdView(context, bannerId, com.facebook.ads.AdSize.BANNER_HEIGHT_50)
            bannerContainer.addView(adView)
            // Request an ad
            adView!!.loadAd()
            // do whatever you want...
            view
        }
    )
}