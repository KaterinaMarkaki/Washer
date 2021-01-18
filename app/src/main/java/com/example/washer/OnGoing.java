package com.example.washer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

    private TextView countDown;
    private Button pause;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillseconds = 60000;//10 min
    private boolean timeRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);

        name = getIntent().getStringExtra("program2");
        TextView chosenName = findViewById(R.id.chosenName);
        chosenName.setText("Επιλεγμένο πρόγραμμα: "+ name);

        temperature = getIntent().getStringExtra("temperature");
        turns = getIntent().getStringExtra("turns");



        TextView chosenTemp = findViewById(R.id.chosenTemp);
        chosenTemp.setText("Επιλεγμένη θερμοκρασία: "+temperature);

        TextView chosenTurns = findViewById(R.id.chosenTurns);
        chosenTurns.setText("Επιλεγμένες στροφές: " + turns);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);

        countDown = findViewById(R.id.countDown);
        pause = findViewById(R.id.pauseBtn);

        startStop();

        updateTimer();
    }

    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OnGoing.this);
                builder.setCancelable(true);
                builder.setTitle("Προσοχή");
                builder.setMessage("Θέλετε να τερματίσετε την πλύση ή να γίνει παύση για λίγο");
                builder.setNegativeButton("Ναι, σταμάτα", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent start = new Intent(OnGoing.this,MainActivity.class);
                        startActivity(start);
                    }
                });

                builder.setPositiveButton("Παύση", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
                        AlertDialog.Builder pause = new AlertDialog.Builder(OnGoing.this);
                        pause.setMessage("Η πλύση βρίσκεται σε παύση");
                        pause.setPositiveButton("Συνέχισε", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                             dialogInterface.cancel();
                            }
                        });
                        pause.show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(OnGoing.this, Information.class);
                startActivity(start);
            }
        });

        /*pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(OnGoing.this,Squeeze.class);
                startActivity(start);
            }
        });*/
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
                Intent start = new Intent(OnGoing.this,Squeeze.class);
                startActivity(start);
            }
        }.start();

        pause.setText("ΠΑΥΣΗ");
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

        countDown.setText(timeLeftText);
    }

    public void stopTimer(){
        countDownTimer.cancel();
        pause.setText("ΣΥΝΕΧΕΙΑ");
        timeRunning = false;
    }
}
