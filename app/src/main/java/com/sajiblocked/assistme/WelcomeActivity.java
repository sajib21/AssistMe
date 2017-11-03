package com.sajiblocked.assistme;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sajiblocked.assistme.R;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIMEOUT);

//        startActivity(new Intent(this, MainActivity.class));
    }
}
