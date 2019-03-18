package com.example.csu.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.csu.demo.adapter.MyFragmentPagerAdapter;
import com.example.csu.demo.fragment.First_fragment;
import com.example.csu.demo.fragment.Fourth_fragment;
import com.example.csu.demo.fragment.Second_fragment;
import com.example.csu.demo.fragment.Third_fragment;

import java.util.ArrayList;
import java.util.List;

public class viewPageDemo_Activity extends FragmentActivity{

    private List<View> viewList;
    private ViewPager viewPager;
    //绑定布局，可以修改title的布局属性
    private PagerTabStrip tabStrip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage_main);
        init();
    }

    private void init() {
        viewPager = findViewById(R.id.viewPage_main);
        tabStrip = findViewById(R.id.tab);

        //为PagerTabStrip设置属性
        tabStrip.setBackgroundColor(Color.GRAY);
        tabStrip.setTextColor(Color.BLACK);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTabIndicatorColor(Color.GREEN);

        /*
         * 1.通过view对象作为viewpager的数据源
         */
        viewList = new ArrayList<>();
        View inflate1 = View.inflate(this, R.layout.viewpage1, null);
        View inflate2 = View.inflate(this, R.layout.viewpage2, null);
        View inflate3 = View.inflate(this, R.layout.viewpage3, null);
        viewList.add(inflate1);
        viewList.add(inflate2);
        viewList.add(inflate3);

        /*
         * 2.通过Fragment作为viewPager数据源
         *
         */
        //创建Fragment适配器数据数组
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new First_fragment());
        fragmentList.add(new Second_fragment());
        fragmentList.add(new Third_fragment());
        fragmentList.add(new Fourth_fragment());


        //为viewPager设置标题
        List<String> titleList = new ArrayList<>();
        titleList.add("最右");
        titleList.add("动态");
        titleList.add("消息");
        titleList.add("我的");


/*
          1.1使用pagerAdapter适配器，去适配页面

        //创建pagerAdapter适配器
        myPagerAdapter myPagerAdapter=new myPagerAdapter(viewList,titleList);

        //viewPager加载适配器
        viewPager.setAdapter(myPagerAdapter);
*/

        /*
         * 2.1Fragment适配器
         */
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);

        viewPager.setAdapter(fragmentPagerAdapter);

        /*
         * 3.1FragmentStateAdapter适配器
         * 未写
         */
    }

}
