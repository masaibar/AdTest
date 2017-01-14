package com.masaibar.adtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

public class FacebookOnlyActivity extends AppCompatActivity {

    private static final String TAG = "FacebookOnlyActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, FacebookOnlyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_only);
        setTitle("Facebook only");

        initAd();
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void initAd() {
        LinearLayout adViewContainer = (LinearLayout) findViewById(R.id.linear_ad_area);

        mAdView = new AdView(this, "240370323076531_240370746409822", AdSize.BANNER_HEIGHT_50);
        adViewContainer.addView(mAdView);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d(
                        TAG,
                        String.format("onError : errorCode = %s, errorMessage = %s",
                                adError.getErrorCode(), adError.getErrorMessage())
                );
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d(TAG, "onAdLoaded");
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.d(TAG, "onAdClicked");
            }
        });
        mAdView.loadAd();
    }
}
