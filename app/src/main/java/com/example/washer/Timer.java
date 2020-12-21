package com.example.washer;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Timer extends AppCompatActivity {

    TextView tvTimer1;
    TextView tvTimer2;

    int t1Hour,t1Minutes;
    int t2Hour,t2Minutes;

    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        tvTimer1 = (TextView) findViewById(R.id.tv_timer1);
        tvTimer2 = (TextView) findViewById(R.id.tv_timer2);

        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Timer.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //hour & min
                                t1Hour = hourOfDay;
                                t1Minutes = minute;
                                //calendar
                                Calendar calendar = Calendar.getInstance();
                                //set hour & min
                                calendar.set(0,0,0,t1Hour,t1Minutes);
                                //set selected time on tv
                                String time = new SimpleDateFormat("hh:mm aa",
                                        Locale.getDefault()).format(new Date());
                                tvTimer1.setText(String.format(time));
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t1Hour,t1Minutes);
                //show dialog
                timePickerDialog.show();

            }
        });

        tvTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Timer.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //hour & min
                                t2Hour = hourOfDay;
                                t2Minutes = minute;
                                //Store hour & min in String
                                String time = t2Hour+":" + t2Minutes;
                                //intialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    //12 hours time format
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");

                                    //set selected time on tv
                                    tvTimer2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                // set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour,t2Minutes);

                timePickerDialog.show();
            }
        });
    }
}
