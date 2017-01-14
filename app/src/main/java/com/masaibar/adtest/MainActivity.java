package com.masaibar.adtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_admob_only).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdmobOnlyActivity.start(getApplicationContext());
            }
        });

        findViewById(R.id.button_facebook_only).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookOnlyActivity.start(getApplicationContext());
            }
        });
    }
}