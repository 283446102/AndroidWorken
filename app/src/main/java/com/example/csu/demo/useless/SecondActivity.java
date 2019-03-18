package com.example.csu.demo.useless;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.csu.demo.R;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {
    private TextView view_calendar;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        init();
    }

    private void init() {
        view_calendar = findViewById(R.id.second_calendar);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MARCH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        view_calendar.setText(year + "-" + month + "-" + day );
        datePicker = findViewById(R.id.datePicker);
        datePicker.init(year, calendar.get(Calendar.MARCH), day, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        view_calendar.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }
        );
    }
}
