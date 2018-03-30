package com.smihajlovski.rewardtask.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.smihajlovski.rewardtask.ui.main.MainActivity;

/**
 * Created by Stefan on 30-Mar-18.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startMainActivity = new Intent(this, MainActivity.class);
        startActivity(startMainActivity);
        finish();
    }
}
