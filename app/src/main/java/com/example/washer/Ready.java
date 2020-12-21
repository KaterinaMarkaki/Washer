package com.example.washer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Ready extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private Button now;
    private Button later;
    private Button cancel;

    private String name;
    private String temperature;
    private String turns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        name = getIntent().getStringExtra("program");
        temperature = getIntent().getStringExtra("temperature");
        turns = getIntent().getStringExtra("turns");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);

        now = (Button) findViewById(R.id.nowBtn);
        later = (Button) findViewById(R.id.laterBtn);
        cancel = (Button) findViewById(R.id.cancelBtn);

    }

    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Ready.this, MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Ready.this, Information.class);
                startActivity(start);
            }
        });

       now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Ready.this,OnGoing.class);
                start.putExtra("program",name);
                start.putExtra("temperature",temperature);
                start.putExtra("turns",turns);
                startActivity(start);
            }
        });

        //later.setOnClickListener();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(Ready.this, step3.class);
            }
        });
    }
}
