package com.example.csu.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.csu.demo.R;

import java.util.Calendar;

public class Second_fragment extends Fragment {
    View view;
    private TextView view_calendar;
    private CalendarView datePickerView;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private String week;
    private String[] strings={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second, container, false);
        init();
        return view;
    }

    private void init() {
        view_calendar = view.findViewById(R.id.second_calendar);
        datePickerView = view.findViewById(R.id.datePicker);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MARCH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        week=strings[calendar.get(Calendar.DAY_OF_WEEK)-1];
        view_calendar.setText(year + "-" + month + "-" + day+" "+week );

        datePickerView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                view_calendar.setText(year+"-"+month+"-"+dayOfMonth+" "+week);
            }
        });
    }
}
