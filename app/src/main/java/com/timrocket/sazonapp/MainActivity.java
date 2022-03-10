package com.timrocket.sazonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  {

    ImageView appName, splashImg;
    LottieAnimationView lottieAnimationView;
    private static int SPLASH_SCREEN = 5400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        appName = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.bg_pink);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-2000).setDuration(1300).setStartDelay(4000);
        appName.animate().translationY(1800).setDuration(1200).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}