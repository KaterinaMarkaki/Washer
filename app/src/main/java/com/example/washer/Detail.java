package com.example.washer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Detail extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;

    private TextView textTemp;
    private TextView textTurns;

    private ListView temperatures;
    private ListView turns;
    //private Integer[] temperaturesArray = { 20, 30, 40, 60, 90 };
    private String[] temperaturesArray;
    //private Integer[] turnsArray = { 400, 600, 800, 1000, 1200};
    private String[] turnsArray;
    private Activity context;
    String savedExtra;
    String temp;
    String turn;

    private Button next;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        savedExtra = getIntent().getStringExtra("program");
        TextView myText = (TextView) findViewById(R.id.textID);
        myText.setText("Επιλεγμένο πρόγραμμα: "+savedExtra);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        home =  findViewById(R.id.navigation_home);
        info =  findViewById(R.id.navigation_info);

        next = (Button) findViewById(R.id.next);

        textTemp = (TextView) findViewById(R.id.textTemp);
        textTurns = (TextView) findViewById(R.id.textTurns);

        temperatures = (ListView) findViewById(R.id.temp);
        turns = (ListView) findViewById(R.id.turns);

        temperaturesArray = getResources().getStringArray(R.array.array_temp);
        turnsArray = getResources().getStringArray(R.array.array_turns);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, temperaturesArray);
        temperatures.setAdapter(adapter);

        temperatures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                    // TODO Auto-generated method stub


            temp = adapter.getItem(position);
            temp = temperaturesArray[position];

            temperatures.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            temperatures.setSelector(android.R.color.holo_blue_light);
            adapter.notifyDataSetChanged();

            textTemp.setText(textTemp.getText() + "" + temperaturesArray[position]);
            textTemp.setVisibility(View.VISIBLE);

            }
                                            });

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, turnsArray);
        turns.setAdapter(adapter2);

        turns.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                turn = adapter2.getItem(position);
                turn = turnsArray[position];

                turns.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                turns.setSelector(android.R.color.holo_blue_light);
                adapter.notifyDataSetChanged();

                textTurns.setText(textTurns.getText()+""+turnsArray[position]);
                textTurns.setVisibility(View.VISIBLE);
            }
        });

    }
    public void onStart() {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Detail.this, MainActivity.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(Detail.this, Information.class);
                startActivity(start);
            }
        });

        //if ((textTurns.getVisibility() == View.VISIBLE) && (textTemp.getVisibility() == View.VISIBLE)){

        next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent start = new Intent(Detail.this,Ready.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("program",savedExtra);
                                        extras.putString("temperature",temp);
                                        extras.putString("turns",turn);
                                        //start.putExtra("program",savedExtra);
                                        //start.putExtra("temperature",temp);
                                        //start.putExtra("turns",turn);
                                        start.putExtras(extras);
                                        startActivity(start);
                                    }
                                }
        );


    }
}