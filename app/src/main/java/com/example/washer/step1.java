package com.example.washer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.w3c.dom.Text;

public class step1 extends AppCompatActivity {

    private BottomNavigationItemView info;
    private BottomNavigationItemView home;
    private Button open;
    private Button close;
    private TextView alertTextViewForOpen;
    private TextView alertTextViewForClose;
    private Button next;

    private StepsView stepBar;
    private String[] steps = {"Αρχή","","","","","","","Τέλος"};

    private int current_state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        stepBar = findViewById(R.id.stepBar);

        stepBar.setLabels(steps)
                .setBarColorIndicator(Color.LTGRAY)
                .setProgressColorIndicator(Color.MAGENTA)
                .setLabelColorIndicator(Color.BLACK)
                .setCompletedPosition(0)
                .drawView();

        stepBar.setCompletedPosition(current_state);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        home =  findViewById(R.id.navigation_home);
        info =  findViewById(R.id.navigation_info);
        open = (Button) findViewById(R.id.openbtn);
        close = (Button) findViewById(R.id.closebtn);
        alertTextViewForOpen = (TextView) findViewById(R.id.AlertTextView);
        alertTextViewForClose = (TextView) findViewById(R.id.AlertTextView2);
        next = (Button) findViewById(R.id.nextbtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_state<(steps.length-1)){
                    current_state = current_state + 1;
                    stepBar.setCompletedPosition(current_state).drawView();
                }
                Log.d("current_state = ",current_state +"");
            }
        });
    }
    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(step1.this,MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(step1.this,Information.class);
                startActivity(start);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(step1.this);
                builder.setCancelable(true);
                builder.setMessage("Παρακαλώ τοποθετήστε τα ρούχα προς πλύση στον κάδο.");
                builder.setNegativeButton("ΚΛΕΙΣΙΜΟ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("ΤΟ ΕΚΑΝΑ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertTextViewForOpen.setVisibility(View.VISIBLE);
                        alertTextViewForClose.setVisibility(View.INVISIBLE);
                    }
                });
                builder.show();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                alertTextViewForOpen.setVisibility(View.INVISIBLE);
                alertTextViewForClose.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(step1.this,step2.class);
                start.putExtra("state",current_state+1);
                startActivity(start);
            }
        });
    }

}