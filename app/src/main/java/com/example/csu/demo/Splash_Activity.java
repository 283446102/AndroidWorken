package com.example.csu.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.csu.demo.Main_Activity;
import com.example.csu.demo.R;

public class Splash_Activity extends AppCompatActivity implements Runnable {
    final Handler mHandler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //设置线程执行时间2s
        mHandler.postDelayed(this,2000);
    }

    @Override
    public void run() {
        Intent intent=new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
