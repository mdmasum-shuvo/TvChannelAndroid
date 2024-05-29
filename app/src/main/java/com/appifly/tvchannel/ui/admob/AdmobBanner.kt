package com.appifly.tvchannel.ui.admob

import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.appifly.tvchannel.BuildConfig
import com.appifly.tvchannel.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

private var adView: com.facebook.ads.AdView? = null



@Composable
fun AdmobBanner(modifier: Modifier = Modifier,isHideAdd: Boolean =true) {
    if (isHideAdd){
        return

    }
    val shouldShowResult = remember {
        mutableStateOf(false)
    }
    Column {
        AndroidView(modifier = modifier.fillMaxWidth(), factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = BuildConfig.BANNER_ADD_ID
                loadAd(AdRequest.Builder().build())
            }
        },
            update = {
                it.apply {
                    loadAd(AdRequest.Builder().build())
                }
                it.adListener = object: AdListener(){
                    override fun onAdClicked() {
                        Log.d("TAG", "onAdClicked: ")
                    }

                    override fun onAdClosed() {
                        Log.d("TAG", "onAdClosed: ")
                    }


                    override fun onAdFailedToLoad(adError : LoadAdError) {
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
    if (shouldShowResult.value){
        FacebookBannerAdsView(BuildConfig.FB_BANNER_ADD_ID)
    }
}

@Composable
fun AdmobBannerAdaptive(modifier: Modifier = Modifier,isHideAdd: Boolean =true) {
    if (isHideAdd){
        return

    }
    val shouldShowResult = remember {
        mutableStateOf(false)
    }
    if (shouldShowResult.value){
        FacebookBannerAdsView(BuildConfig.FB_BANNER_ADD_ID)
    }
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

        },
            update = {

                it.adListener = object: AdListener(){
                    override fun onAdClicked() {
                        Log.d("TAG", "onAdClicked: ")
                    }

                    override fun onAdClosed() {
                        Log.d("TAG", "onAdClosed: ")
                    }

                    override fun onAdFailedToLoad(adError : LoadAdError) {
                        shouldShowResult.value = true
                        adView?.visibility = android.view.View.VISIBLE
                    }

                    override fun onAdImpression() {
                        Log.d("TAG", "onAdImpression: ")
                    }

                    override fun onAdLoaded() {
                        shouldShowResult.value = false
                        adView?.visibility = android.view.View.GONE
                    }

                    override fun onAdOpened() {
                        Log.d("TAG", "onAdOpened: ")
                    }
                }
            })
        Spacer(modifier = Modifier.height(16.dp))

    }


// Step 3: Load an ad.



}

@Composable
fun FacebookBannerAdsView(bannerId : String) {
    AndroidView(
        factory = { context ->
            // Create and configure your Android View here
            val view = LayoutInflater.from(context).inflate(R.layout.facebook_ads, null, false)
            val bannerContainer = view.findViewById<LinearLayout>(R.id.banner_container)
            adView = com.facebook.ads.AdView(context, bannerId, com.facebook.ads.AdSize.BANNER_HEIGHT_50)
            bannerContainer.addView(adView)
            // Request an ad
            adView!!.loadAd()
            // do whatever you want...
            view
        },
        update = { view ->
            // Update the Android View if needed
        }
    )
}