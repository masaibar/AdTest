package com.masaibar.adtest;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;

import com.facebook.ads.*;


public class FacebookCustomEventBanner implements CustomEventBanner {

    private static final String TAG = "FBCustomEventBanner";

    private AdView anBanner;

    @Override
    public void onDestroy() {
        if (anBanner != null) {
            anBanner.destroy();
        }
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void requestBannerAd(Context context,
                                CustomEventBannerListener listener,
                                String serverParameter,
                                AdSize size,
                                MediationAdRequest mediationAdRequest,
                                Bundle customEventExtras) {

        Log.d(TAG, String.format("Mediation requestBannerAd parameter = %s", serverParameter));

        anBanner = new AdView(context, serverParameter, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        anBanner.setAdListener(new FacebookCustomEventBannerForwarder(listener, anBanner));
        anBanner.loadAd();
    }
}
