package com.example.csu.demo.useless;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.csu.demo.R;

import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity {
    private TextView third_time;
    private TimePicker timePicker;
    private Calendar calendar;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        init();

    }

    private void init() {
        third_time = findViewById(R.id.third_time);
        timePicker = findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        third_time.setText(hour+":"+minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                third_time.setText(hourOfDay + ":" + minute);
            }
        });
    }
}
