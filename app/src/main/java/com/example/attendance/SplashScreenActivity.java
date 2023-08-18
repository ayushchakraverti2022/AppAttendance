package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class SplashScreenActivity extends AppCompatActivity {
    public LinearProgressIndicator linearProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        linearProgressIndicator = findViewById(R.id.linearProgressIndicator);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        }, 2000);

    }
}