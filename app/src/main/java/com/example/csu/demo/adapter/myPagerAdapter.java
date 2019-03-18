package com.example.csu.demo.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class myPagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> titleList;
    public myPagerAdapter(List<View> viewList,List<String> titleList){
        this.viewList=viewList;
        this.titleList=titleList;
    }
    @Override
    //返回pager页卡的数量
    public int getCount() {
        return viewList.size();
    }

    @Override
    //判断view是否来自于对象
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    //实例化一个页卡
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //添加数据源
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //销毁一个页卡
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //界面删除数据
        container.removeView(viewList.get(position));
    }

    /**
     * 设置viewPager title的标题
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
