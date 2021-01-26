package com.example.washer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.anton46.stepsview.StepsView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class Ready extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private Button now;
    private Button later;
    private Button cancel;
    private Button nightTimeBtn;

    private String name;
    private String temperature;
    private String turns;
    private int current_state;
    private StepsView stepBar;
    private String[] steps = {"","","","","Ρύθμιση ώρας","","","Τέλος"};
    String nightTime = "11:00:00 AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        name = getIntent().getStringExtra("program");
        temperature = getIntent().getStringExtra("temperature");
        turns = getIntent().getStringExtra("turns");

        current_state = getIntent().getIntExtra("state",0);
        stepBar = findViewById(R.id.stepBar);

        stepBar.setLabels(steps)
                .setBarColorIndicator(Color.LTGRAY)
                .setProgressColorIndicator(Color.MAGENTA)
                .setLabelColorIndicator(Color.BLACK)
                .setCompletedPosition(0)
                .drawView();

        stepBar.setCompletedPosition(current_state);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home = findViewById(R.id.navigation_home);
        info = findViewById(R.id.navigation_info);

        now = findViewById(R.id.nowBtn);
        later = findViewById(R.id.laterBtn);
        cancel = findViewById(R.id.cancelBtn);
        nightTimeBtn = findViewById(R.id.nightBtn);

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
                start.putExtra("program2",name);
                start.putExtra("temperature",temperature);
                start.putExtra("turns",turns);
                start.putExtra("state",current_state+1);
                startActivity(start);
            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(Ready.this,Timer.class);
                startActivity(start);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Ready.this);
                builder.setCancelable(true);
                builder.setTitle("ΠΡΟΣΟΧΗ");
                builder.setMessage("Η πλύση σας θα ακυρωθεί. Είστε σίγουρος;");
                builder.setNegativeButton("ΟΧΙ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("ΝΑΙ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent start = new Intent(Ready.this, step3.class);
                        startActivity(start);
                    }
                });
                builder.show();

            }
        });

        nightTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY,17);
                cal.set(Calendar.MINUTE,30);
                cal.set(Calendar.SECOND,0);
                cal.set(Calendar.MILLISECOND,0);

                Date d = cal.getTime();


                //creating Calendar instance
                Calendar calendar = Calendar.getInstance();
                //Returns current time in millis
                long timeMilli2 = calendar.getTimeInMillis();
                long diff = timeMilli2 - d.getTime();
                System.out.println(d);
                System.out.println(calendar.getTime());
                System.out.println(diff);
                /*DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

                String formattedDate = myObj.format(myFormatObj);

                Date currentTime = Calendar.getInstance().getTime();
                long diff = currentTime.getTime() - cal.getTime();
                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);
                System.out.println("Time in seconds: " + diffSeconds + " seconds.");
                System.out.println("Time in minutes: " + diffMinutes + " minutes.");
                System.out.println("Time in hours: " + diffHours + " hours.");*/

                /*SimpleDateFormat sdf = new SimpleDateFormat("K:mm:ss a");

                Date currentTime = Calendar.getInstance().getTime();

                String time2 = sdf.format(currentTime);

                System.out.println(nightTime);
                System.out.println(time2);

                int diffhours = 0;
                int diffmin = 0;

                try {
                    Date dateObj1 = sdf.parse(nightTime);
                    Date dateObj2 = sdf.parse(time2);

                    System.out.println(nightTime);
                    System.out.println(time2);

                    DecimalFormat Formatter = new DecimalFormat("###,###");

                    long diff = dateObj1.getTime() - dateObj2.getTime();
                    diffhours = (int) (diff / (60 * 60 * 1000));
                    if(diffhours < 0) diffhours = 24 + diffhours;
                    System.out.println("difference between hours: " + Formatter.format(diffhours));


                    diffmin = (int) (diff / (60 * 1000));

                    if(diffmin < 0) diffmin = 24 * 60 - diffmin;

                    if(diffhours > 0) diffmin = diffmin / diffhours /60;

                    System.out.println("difference between minutes: " + Formatter.format(diffmin));

                    int diffsec = (int) (diff / (1000));
                    if(diffsec<0) diffsec = 24*60*60 + diffsec;

                    if(diffhours>0) diffsec = diffsec / diffhours / 60 / 60;
                    if(diffmin>0) diffsec = diffsec / diffmin / 60 ;

                    System.out.println("difference between seconds: " + Formatter.format(diffsec));
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

                Intent start = new Intent(Ready.this,Squeeze_timer.class);
                start.putExtra("code",0);
                start.putExtra("mill",diff);
                startActivity(start);
            }
        });

    }
}
