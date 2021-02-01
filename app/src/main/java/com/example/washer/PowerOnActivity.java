package com.example.washer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class PowerOnActivity extends AppCompatActivity {

    private ImageButton powerButton;
    private ImageButton soundBtn;
    public Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_on);
        powerButton = (ImageButton) findViewById(R.id.btnCircle);
        soundBtn = (ImageButton) findViewById(R.id.sound);

        flag = true;

        soundBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                soundBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_background));
                soundBtn.setImageDrawable(getResources().getDrawable(R.drawable.sound_off_removebg_preview));
                flag = false;
            }
        });
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