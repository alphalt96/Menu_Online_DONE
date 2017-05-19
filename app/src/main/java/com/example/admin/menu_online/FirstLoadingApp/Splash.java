package com.example.admin.menu_online.FirstLoadingApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.menu_online.MainActivity;
import com.example.admin.menu_online.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000);
                } catch (Exception e) {

                } finally {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}
