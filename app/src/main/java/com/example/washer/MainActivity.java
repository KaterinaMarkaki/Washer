package com.example.washer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView tshirt;
    private ImageView info;

    private TextView programTxt;
    private TextView infoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tshirt = (ImageView) findViewById(R.id.tshirt_icon);
        info = (ImageView) findViewById(R.id.imageView3);

        programTxt = (TextView) findViewById(R.id.programTxt);
        infoTxt = (TextView) findViewById(R.id.infoTxt);

    }
    public void onStart() {
        super.onStart();

        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(MainActivity.this,step1.class);
                startActivity(start);
            }
        });

        programTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(MainActivity.this,step1.class);
                startActivity(start);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(MainActivity.this,Information.class);
                startActivity(start);
            }
        });

        infoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(MainActivity.this,Information.class);
                startActivity(start);
            }
        });

    }
}