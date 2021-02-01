package com.example.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Done extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;
    private Button on;
    private Button off;
    private ImageButton turnOff;
    private TextView alertTextOpen;
    private TextView alertTextClose;
    MediaPlayer mp;

    private int current_state;
    private StepsView stepBar;
    private String[] steps = {"","","","","","","","Τέλος"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        current_state = getIntent().getIntExtra("state",0);
        stepBar = findViewById(R.id.stepBar);

        stepBar.setLabels(steps)
                .setBarColorIndicator(Color.LTGRAY)
                .setProgressColorIndicator(Color.MAGENTA)
                .setLabelColorIndicator(Color.BLACK)
                .setCompletedPosition(0)
                .drawView();

        stepBar.setCompletedPosition(current_state);

        mp = MediaPlayer.create(this,R.raw.tune);
        mp.start();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);
        on = (Button) findViewById(R.id.openBtn);
        off = (Button) findViewById(R.id.closeBtn);
        turnOff = (ImageButton) findViewById(R.id.deactivationBtn);
        alertTextOpen = (TextView) findViewById(R.id.open);
        alertTextClose = (TextView) findViewById(R.id.close);
    }

    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Done.this, MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Done.this, Information.class);
                startActivity(start);
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertTextOpen.setVisibility(View.VISIBLE);
                alertTextClose.setVisibility(View.INVISIBLE);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                alertTextOpen.setVisibility(View.INVISIBLE);
                alertTextClose.setVisibility(View.VISIBLE);
            }
        });

        turnOff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick( View view) {
                stopPlayer();
                Intent start = new Intent(Done.this,PowerOnActivity.class);
                startActivity(start);
            }
        });



    }

    private void stopPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}