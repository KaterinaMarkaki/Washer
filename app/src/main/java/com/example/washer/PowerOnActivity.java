package com.example.washer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class PowerOnActivity extends AppCompatActivity {

    private ImageButton powerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_on);
        powerButton = (ImageButton) findViewById(R.id.btnCircle);
    }

    public void onStart() {
        super.onStart();

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(PowerOnActivity.this,MainActivity.class);
                startActivity(start);
            }
        });

    }
}