package com.masaibar.adtest;

import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

/**
 * Created by masaibar on 2017/01/14.
 */

public class FacebookCustomEventBannerForwarder implements AdListener {

    private final static String TAG = "FBCustomBanner";
    private CustomEventBannerListener mBannerListener;
    private AdView mAdView;

    public FacebookCustomEventBannerForwarder(
            CustomEventBannerListener listener, AdView adView) {
        this.mBannerListener = listener;
        this.mAdView = adView;
    }

    @Override
    public void onAdLoaded(Ad ad) {
        Log.d(TAG, "FacebookCustomEventBanner loaded!");
        mBannerListener.onAdLoaded(mAdView);
    }

    @Override
    public void onAdClicked(Ad ad) {
        Log.d(TAG, "FacebookCustomEventBanner clicked!");
        mBannerListener.onAdClicked();
        mBannerListener.onAdOpened();
        mBannerListener.onAdLeftApplication();
    }

    @Override
    public void onError(Ad ad, AdError error) {
        Log.d(TAG, "FacebookCustomEventBanner Error:" + error.getErrorMessage());
        switch (error.getErrorCode()) {
            case AdError.INTERNAL_ERROR_CODE:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INTERNAL_ERROR);
                break;
            case AdError.NETWORK_ERROR_CODE:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NETWORK_ERROR);
                break;
            default:
                mBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_NO_FILL);
                break;
        }
    }
}
