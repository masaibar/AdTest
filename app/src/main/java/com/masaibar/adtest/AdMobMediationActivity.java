package com.masaibar.adtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdMobMediationActivity extends AppCompatActivity {

    private static final String TAG = "AdMobMediationActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, AdMobMediationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediation);

        findViewById(R.id.button_reload_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAd();
            }
        });

        setTitle("AdMob Mediation");
        initAd();
    }

    private void initAd() {
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1115073286529988~6752511158");
        mAdView = (AdView) findViewById(R.id.adview_mediation_banner);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d(TAG, "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d(TAG, "onLeftApplication");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.d(TAG, "onAdFailedToLoad "+ i);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.d(TAG, "onAdClosed");
            }
        });
        mAdView.loadAd(request);
    }
}
