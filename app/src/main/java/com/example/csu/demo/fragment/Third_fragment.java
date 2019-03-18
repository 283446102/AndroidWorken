package com.example.csu.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.csu.demo.R;

import java.util.Calendar;

public class Third_fragment extends Fragment {
    View view;
    private TextView third_time;
    private TimePicker timePicker;
    private Calendar calendar;
    private int hour;
    private int minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third, container, false);
        init();
        return view;
    }

    private void init() {
        third_time = view.findViewById(R.id.third_time);
        timePicker = view.findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        third_time.setText(hour + ":" + minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                third_time.setText(hourOfDay + ":" + minute);
            }
        });
    }
}
