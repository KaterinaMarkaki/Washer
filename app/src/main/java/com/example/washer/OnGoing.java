package com.example.washer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnGoing extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private String name;
    private String temperature;
    private String turns;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);
        Bundle extras = getIntent().getExtras();
        name =extras.getString("program");

        TextView choosenName = (TextView) findViewById(R.id.choosenName);
        choosenName.setText("Επιλεγμένο πρόγραμμα: "+name);

        temperature = getIntent().getStringExtra("temperature");

        TextView choosenTemp = (TextView) findViewById(R.id.choosenTemp);
        choosenName.setText("" + choosenTemp);

        turns = getIntent().getStringExtra("turns");
        TextView choosenTurns = (TextView) findViewById(R.id.choosenTurns);
        choosenName.setText("" + choosenTurns);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);
    }

    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(OnGoing.this, MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(OnGoing.this, Information.class);
                startActivity(start);
            }
        });
    }
}
