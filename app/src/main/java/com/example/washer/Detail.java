package com.example.washer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Detail extends AppCompatActivity {
    private BottomNavigationItemView info;
    private BottomNavigationItemView home;


    private ListView temperatures;
    private ListView turns;
    private String[] temperaturesArray;
    private String[] turnsArray;
    private Activity context;
    int selectedPos;
    String savedExtra;
    String temp;
    String turn;

    private int current_state;
    private StepsView stepBar;
    private String[] steps = {"","","","Ρύθμιση πλύσης","","","","Τέλος"};

    int [] suggestedTemp = {40,20,60,60,20,40};
    int [] suggestedTurns = {800,400,800,400,800,800};

    private TextView suggTemp;
    private TextView suggTurns;
    private Button next;
    
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        savedExtra = getIntent().getStringExtra("program");
        TextView myText = (TextView) findViewById(R.id.textID);
        myText.setText("Επιλεγμένο πρόγραμμα: "+savedExtra);

        current_state = getIntent().getIntExtra("state",0);
        selectedPos = getIntent().getIntExtra("position", 0);
        suggTemp = (TextView) findViewById(R.id.SuggTemp) ;
        suggTurns = (TextView) findViewById(R.id.SuggTurns) ;

        suggTemp.setText("Θερμοκρασία: " + suggestedTemp[selectedPos]+"°C");
        suggTurns.setText("Στροφές: "+ suggestedTurns[selectedPos]);

        stepBar = findViewById(R.id.stepBar4);

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

        home =  findViewById(R.id.navigation_home);
        info =  findViewById(R.id.navigation_info);

        next = (Button) findViewById(R.id.next);

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
            //   temperatures.setSelection(selectedPos);
            temperatures.setSelector(android.R.color.background_light);
            adapter.notifyDataSetChanged();

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
                turns.setSelector(android.R.color.background_light);
                adapter.notifyDataSetChanged();

            }
        });

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
                                        start.putExtra("state",current_state+1);
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