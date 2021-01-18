package com.example.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Squeeze_timer extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillseconds = 60000;//10 min
    private boolean timeRunning;

    private Button btn;
    private TextView min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squeeze_timer);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);

        btn = findViewById(R.id.Btn);
        min = findViewById(R.id.min);

        startStop();

        updateTimer();
    }

    public void onStart(){
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Squeeze_timer.this, MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Squeeze_timer.this, Information.class);
                startActivity(start);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(Squeeze_timer.this,Done.class);
                startActivity(start);
            }
        });
    }

    public void startStop(){
        if(timeRunning){
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillseconds,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Intent start = new Intent(Squeeze_timer.this,Done.class);
                startActivity(start);
            }
        }.start();

        btn.setText("ΠΑΥΣΗ");
        timeRunning = true;
    }

    private void updateTimer() {
        int minutes = (int) timeLeftInMillseconds/60000;
        int seconds = (int) timeLeftInMillseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText = timeLeftText + ":";
        if(seconds < 10) timeLeftText +="0";
        timeLeftText += seconds;

        min.setText(timeLeftText);
    }

    public void stopTimer(){
        countDownTimer.cancel();
        btn.setText("ΣΥΝΕΧΕΙΑ");
        timeRunning = false;
    }
}