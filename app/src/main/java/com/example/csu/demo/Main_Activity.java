package com.example.csu.demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.app.Notification.Builder;

import com.example.csu.demo.fragment.First_fragment;
import com.example.csu.demo.fragment.Fourth_fragment;
import com.example.csu.demo.fragment.Second_fragment;
import com.example.csu.demo.fragment.Third_fragment;
import com.example.csu.library.NaviView;

public class Main_Activity extends AppCompatActivity {
    NotificationManager manager;
    private static final int Notification_ID = 1001;
    /*    //保存MyTouchListener接口的列表
        private ArrayList<MyTouchListener> myTouchListeners=new ArrayList<MyTouchListener>();*/
    FragmentManager fragmentManager;
    FragmentTransaction beginTransaction;
    private First_fragment first_fragment;
    private Second_fragment second_fragment;
    private Third_fragment third_fragment;
    private Fourth_fragment fourth_fragment;
    float lastX;
    float lastY;
    private static int CHECKED = 0;
    private static int UNCHECKED = 1;

    private NaviView naviView_first;
    private NaviView naviView_second;
    private NaviView naviView_third;
    private NaviView naviView_fourth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initEvent();
        init();

    }

    /**
     * 对控件初始化
     */
    private void initEvent() {
        naviView_first = findViewById(R.id.naviView_first_main);
        naviView_second = findViewById(R.id.naviView_second_main);
        naviView_third = findViewById(R.id.naviView_third_main);
        naviView_fourth = findViewById(R.id.naviView_fourth_main);
    }
/*    //声明一个MyTouchListener接口，监听fragment的onTouchEvent事件
    public interface MyTouchListener{
        public void onTouchEvent(MotionEvent event);
    }
    //注册事件
    public void registerMyTouchListener(MyTouchListener listener){
        myTouchListeners.add(listener);
    }
    //注销事件
    public void unRegisterMyTouchListener(MyTouchListener listener){
        myTouchListeners.add(listener);
    }

    //分发触摸事件给所有注册了MyTouchEvent的接口
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener:myTouchListeners){
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }*/

    private void init() {
        /**
         * 一开始进入的页面进行初始化
         */
        fragmentManager = getSupportFragmentManager();
        beginTransaction = fragmentManager.beginTransaction();
        first_fragment = new First_fragment();
        beginTransaction.replace(R.id.main_RelativeLayout, first_fragment);
        beginTransaction.commit();

        //获取通知栏服务
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        naviView_first.setBigIcon(R.drawable.big_zuiyou_normal);
        naviView_first.setSmallIcon(R.drawable.small_zuiyou_normal);
        naviView_first.check();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 50);
       /* Drawable drawable_first = getResources().getDrawable(R.drawable.first_icon);
        drawable_first.setBounds(0, 0, 75, 75);
        radioButton_first.setCompoundDrawables(null, drawable_first, null, null);

        Drawable drawable_second = getResources().getDrawable(R.drawable.second_icon);
        drawable_second.setBounds(0, 0, 75, 75);
        radioButton_second.setCompoundDrawables(null, drawable_second, null, null);

        Drawable drawable_third = getResources().getDrawable(R.drawable.third_icon);
        drawable_third.setBounds(0, 0, 75, 75);
        radioButton_third.setCompoundDrawables(null, drawable_third, null, null);

        Drawable drawable_fourth = getResources().getDrawable(R.drawable.fourth_icon);
        drawable_fourth.setBounds(0, 0, 75, 75);
        radioButton_fourth.setCompoundDrawables(null, drawable_fourth, null, null);*/

    }

    public void onClick(View v) {
        Animation animation = getScale();
        resetIcon();
        switch (v.getId()) {
            case R.id.naviView_first_main:
                //显示通知栏信息
                sendNotification();

                naviView_first.setBigIcon(R.drawable.big_zuiyou_pressed);
                naviView_first.setSmallIcon(R.drawable.small_zuiyou_pressed);
                fragmentManager = getSupportFragmentManager();
                beginTransaction = fragmentManager.beginTransaction();
                first_fragment = new First_fragment();
                beginTransaction.replace(R.id.main_RelativeLayout, first_fragment);
                beginTransaction.commit();
                naviView_first.startAnimation(animation);
                break;
            case R.id.naviView_second_main:
                //取消通知栏信息
                manager.cancel(Notification_ID);

                naviView_second.setBigIcon(R.drawable.big_dynamic_pressed);
                naviView_second.setSmallIcon(R.drawable.small_dynamic_pressed);
                fragmentManager = getSupportFragmentManager();
                beginTransaction = fragmentManager.beginTransaction();
                second_fragment = new Second_fragment();
                beginTransaction.replace(R.id.main_RelativeLayout, second_fragment);
                beginTransaction.commit();
                naviView_second.startAnimation(animation);
                break;
            case R.id.naviView_third_main:
                naviView_third.setBigIcon(R.drawable.big_message_pressed);
                naviView_third.setSmallIcon(R.drawable.small_message_pressed);
                fragmentManager = getSupportFragmentManager();
                beginTransaction = fragmentManager.beginTransaction();
                third_fragment = new Third_fragment();
                beginTransaction.replace(R.id.main_RelativeLayout, third_fragment);
                beginTransaction.commit();
                naviView_third.startAnimation(animation);
                break;
            case R.id.naviView_fourth_main:
                naviView_fourth.setBigIcon(R.drawable.big_mine_pressed);
                naviView_fourth.setSmallIcon(R.drawable.small_mine_pressed);
                fragmentManager = getSupportFragmentManager();
                beginTransaction = fragmentManager.beginTransaction();
                fourth_fragment = new Fourth_fragment();
                beginTransaction.replace(R.id.main_RelativeLayout, fourth_fragment);
                beginTransaction.commit();
                naviView_fourth.startAnimation(animation);
                break;
        }
    }

    /*
    点击导航栏图标，图标的缩放动画
     */
    private Animation getScale() {
        ScaleAnimation animation = new ScaleAnimation(0.8f, 1, 0.8f, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        animation.setInterpolator(this, android.R.anim.bounce_interpolator);
        return animation;
    }

    /*
    底下导航栏的图片资源
     */
    private void resetIcon() {
        naviView_first.setBigIcon(R.drawable.big_zuiyou_normal);
        naviView_first.setSmallIcon(R.drawable.small_zuiyou_normal);

        naviView_second.setBigIcon(R.drawable.big_dynamic_normal);
        naviView_second.setSmallIcon(R.drawable.small_dynamic_normal);

        naviView_third.setBigIcon(R.drawable.big_message_normal);
        naviView_third.setSmallIcon(R.drawable.small_message_normal);

        naviView_fourth.setBigIcon(R.drawable.big_mine_normal);
        naviView_fourth.setSmallIcon(R.drawable.small_mine_normal);
    }

    /**
     * 构造notification并发送到通知栏
     */
    private void sendNotification() {
        Intent intent = new Intent(this, ViewFlipperDemo_Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.icon);//设置图标
        builder.setTicker("你有一万元待收入");//手机状态栏提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("紧急通知");//设置标题
        builder.setContentText("20元纸币价值5000元，记住它是这样子的，谁能...");//设置通知内容
        builder.setContentIntent(pendingIntent);//点击后的意图
/*        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置提示指示灯
        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动*/
        builder.setDefaults(Notification.DEFAULT_ALL);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1001", "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
            builder.setChannelId("1001");
        }
        //当android版本在4.1以上时
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        manager.notify(Notification_ID, notification);
    }
}
