package com.appifly.tvchannel.ui.admob

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.appifly.tvchannel.BuildConfig
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

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
