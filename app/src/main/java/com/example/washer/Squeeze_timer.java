package com.example.washer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Squeeze_timer extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillseconds;//10 min
    private boolean timeRunning;

    private Button btn;
    private TextView min;
    private TextView text;

    private int code;
    private int current_state;
    private StepsView stepBar;
    private String[] steps = {"","","","","","","Στύψιμο",""};

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

        current_state = getIntent().getIntExtra("state",0);
        code = getIntent().getIntExtra("code",0);

        text = findViewById(R.id.mes);

        if(code==1){
            text.setText("Τα ρούχα θα είναι έτοιμα σε...");
            timeLeftInMillseconds = 60000;//10 min
        } else if (code==0){
            text.setText("Η πλύση θα ξεκινήσει σε...");
            timeLeftInMillseconds = getIntent().getLongExtra("mill",0);
        }

        stepBar = findViewById(R.id.stepBar);

        stepBar.setLabels(steps)
                .setBarColorIndicator(Color.LTGRAY)
                .setProgressColorIndicator(Color.MAGENTA)
                .setLabelColorIndicator(Color.BLACK)
                .setCompletedPosition(0)
                .drawView();

        stepBar.setCompletedPosition(current_state);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(Squeeze_timer.this);
                builder.setCancelable(true);
                builder.setMessage("Το στύψιμο των ρούχων διακόπηκε προσωρινά.");
                builder.setNegativeButton("ΣΤΑΜΑΤΑ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // dialogInterface.cancel();
                        Intent start = new Intent(Squeeze_timer.this,Squeeze.class);
                        startActivity(start);
                    }
                });

                builder.setPositiveButton("ΣΥΝΕΧΙΣΕ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent start = new Intent(Squeeze_timer.this,Done.class);
                        start.putExtra("state",current_state+1);
                    }
                });
                builder.show();

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