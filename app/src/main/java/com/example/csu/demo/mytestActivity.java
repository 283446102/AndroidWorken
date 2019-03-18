package com.example.csu.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.csu.library.NaviView;

public class mytestActivity extends AppCompatActivity {
    private NaviView fisrt_view;
    private NaviView second_view;
    private NaviView third_view;
    private NaviView fourth_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        fisrt_view = findViewById(R.id.naviView_first);
        second_view = findViewById(R.id.naviView_second);
        third_view = findViewById(R.id.naviView_third);
        fourth_view = findViewById(R.id.naviView_fourth);


        fisrt_view.setBigIcon(R.drawable.big_zuiyou_normal);
        fisrt_view.setSmallIcon(R.drawable.small_zuiyou_normal);
        fisrt_view.check();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },50);
    }
    public void onClick(View view){
        resetIcon();
        switch (view.getId()){
            case R.id.naviView_first:
                fisrt_view.setBigIcon(R.drawable.big_zuiyou_pressed);
                fisrt_view.setSmallIcon(R.drawable.small_zuiyou_pressed);
                break;
            case R.id.naviView_second:
                second_view.setBigIcon(R.drawable.big_dynamic_pressed);
                second_view.setSmallIcon(R.drawable.small_dynamic_pressed);
                break;
            case R.id.naviView_third:
                third_view.setBigIcon(R.drawable.big_message_pressed);
                third_view.setSmallIcon(R.drawable.small_message_pressed);
                break;
            case R.id.naviView_fourth:
                fourth_view.setBigIcon(R.drawable.big_mine_pressed);
                fourth_view.setSmallIcon(R.drawable.small_mine_pressed);
                break;
        }
    }
    private void resetIcon(){
        fisrt_view.setBigIcon(R.drawable.big_zuiyou_normal);
        fisrt_view.setSmallIcon(R.drawable.small_zuiyou_normal);

        second_view.setBigIcon(R.drawable.big_dynamic_normal);
        second_view.setSmallIcon(R.drawable.small_dynamic_normal);

        third_view.setBigIcon(R.drawable.big_message_normal);
        third_view.setSmallIcon(R.drawable.small_message_normal);

        fourth_view.setBigIcon(R.drawable.big_mine_normal);
        fourth_view.setSmallIcon(R.drawable.small_mine_normal);
    }
}
