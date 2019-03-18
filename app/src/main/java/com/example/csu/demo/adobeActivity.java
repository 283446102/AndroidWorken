package com.example.csu.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class adobeActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private ProgressBar progressBar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adobe);
        init();
    }

    private void init() {
        TextView view = findViewById(R.id.textView2);
        view.setText("你好啊");
        ImageButton test_imageButton = findViewById(R.id.test_imageButton);
        test_imageButton.setOnClickListener(this);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar1.setMax(100);
        progressBar1.setProgress(0);
        progressBar1.setSecondaryProgress(0);
        if (progressBar1.getProgress() <= 100) {
            time1();
        }
        if (progressBar1.getSecondaryProgress() <= 100) {
            time2();
        }
    }

    private void time1() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressBar1.incrementProgressBy(1);
            }
        }, 0, 100);
    }

    private void time2() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressBar1.incrementSecondaryProgressBy(1);
            }
        }, 0, 70);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_imageButton:
                break;
        }
    }
}
