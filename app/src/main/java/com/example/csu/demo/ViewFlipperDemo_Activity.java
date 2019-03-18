package com.example.csu.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class ViewFlipperDemo_Activity extends AppCompatActivity {
    private boolean isChange;
    private float mLastX;
    private int[] resId = {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4};
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewflipper_main);
        //初始化控件
        init();
    }

    private void init() {
        viewFlipper = findViewById(R.id.ViewFlipper_main);
        for (int aResId : resId) {
            //将图片资源添加到viewFlipper中
            viewFlipper.addView(getImageView(aResId));
        }
    }


    /**
     * 将图片作为数据源
     */
    private View getImageView(int i) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(i);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //让mLastX获取一开始手指点击的X坐标
                isChange=true;
                mLastX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isChange){
                    if (event.getX() - mLastX > 200) {
                        //往右滑，翻到上一页
                        viewFlipper.setInAnimation(this, R.anim.left_in);
                        viewFlipper.setOutAnimation(this, R.anim.left_out);
                        viewFlipper.showPrevious();
                        //设置阻值，让if语句只执行一次
                        Toast.makeText(this,"翻到上一页被执行了",Toast.LENGTH_SHORT).show();
                        Log.i("tag","翻到上一页被执行了");
                        isChange=false;
                    }
                }
                if (isChange){
                    //往左滑，翻到下一页
                    if (mLastX - event.getX() > 200) {
                        viewFlipper.setInAnimation(this, R.anim.right_in);
                        viewFlipper.setOutAnimation(this, R.anim.right_out);
                        viewFlipper.showNext();
                        //设置阻值，让if语句只执行一次
                        Toast.makeText(this,"翻到下一页被执行了",Toast.LENGTH_SHORT).show();
                        Log.i("tag","翻到下一页被执行了");
                        isChange=false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
    /*
      translate动画
     */
/*    private Animation left_in(){
        TranslateAnimation animation=new TranslateAnimation(-1200,0,0,0) ;
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }
    private Animation left_out(){
        TranslateAnimation animation=new TranslateAnimation(0,1200,0,0) ;
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }*/
}
