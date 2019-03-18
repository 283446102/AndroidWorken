package com.example.csu.demo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class music_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView previous_imagView;
    private ImageView play_imageView;
    private ImageView next_imageView;
    private ProgressBar music_progressBar;
    private MediaPlayer mediaPlayer =null;
    private boolean isRelease =true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        init();
    }

    private void init() {
        play_imageView = findViewById(R.id.play_imageView);
        previous_imagView = findViewById(R.id.previous_imageView);
        next_imageView = findViewById(R.id.next_imageView);
        music_progressBar = findViewById(R.id.music_bar);

        play_imageView.setOnClickListener(this);
        previous_imagView.setOnClickListener(this);
        next_imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_imageView:
                if (isRelease){
//                    mediaPlayer = MediaPlayer.create(this,R.raw.music_test);
                    isRelease = false;
                }

                music_progressBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();//开始播放
                if (mediaPlayer.isPlaying()){
                    play_imageView.setImageResource(R.drawable.pause);
                }
                break;
            case R.id.previous_imageView:
                break;
            case R.id.next_imageView:
                break;
        }
    }
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            music_progressBar.setProgress(msg.what);
        }
    };
    class MusicThread implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            handler.sendEmptyMessage(mediaPlayer.getCurrentPosition());
        }
    }
}
