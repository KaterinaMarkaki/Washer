package com.example.washer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class step3 extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    String[] nameArray = {"ΣΚΟΥΡΟΧΡΩΜΑ","ΜΑΛΛΙΝΑ"};
    String[] infoArray = {"έχουν όλα σκούρο χρώμα.","είναι όλα μάλλινα."};
    Integer[] imageArray = {R.drawable.darkclothes_removebg_preview,R.drawable.wool_removebg_preview};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        home =  findViewById(R.id.navigation_home);
        info =  findViewById(R.id.navigation_info);

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, imageArray);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(whatever);

    }
    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(step3.this,MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(step3.this,Information.class);
                startActivity(start);
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(step3.this);
                builder.setCancelable(true);
                builder.setTitle("ΟΔΗΓΙΑ");
                builder.setMessage("Επιλέξτε αυτή τη λειτουργία αν όλα τα ρούχα που θέλετε να πλύνετε "+infoArray[position]);
                builder.setNegativeButton("OXI, ΔΕΝ ΤΟ ΘΕΛΩ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("ΝΑΙ, ΤΟ ΘΕΛΩ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(step3.this, Detail.class);
                        String program = nameArray[position];
                        intent.putExtra("program",program);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}
