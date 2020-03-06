package com.thrymr.farmersmarket.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.thrymr.farmersmarket.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(this:: callLogin,1000);
    }

    private void callLogin() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
