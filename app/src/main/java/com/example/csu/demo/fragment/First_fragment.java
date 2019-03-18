package com.example.csu.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.csu.demo.useless.FourthActivity;
import com.example.csu.demo.R;
import com.example.csu.demo.useless.SecondActivity;
import com.example.csu.demo.useless.ThirdActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class First_fragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    View view;
    private boolean isLastLow = false;
    private SimpleAdapter SimpleAdapter;
    private List<Map<String, Object>> data;
    private String[] iconName = {"我的桌面", "我的日历", "当前时间", "IPone X", "一加 6 plus", "中兴", "华为Mate20"};
    private int[] icon = {R.drawable.desk, R.drawable.calendar, R.drawable.clock, R.drawable.phone4, R.drawable.phone5, R.drawable.phone6, R.drawable.phone7};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);
        init();
        return view;
    }

    private void init() {
        data = new ArrayList<>();
        /**
         * 声明listView
         */
        ListView listView = view.findViewById(R.id.ListView);
        SimpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.item, new String[]{"image", "text"}, new int[]{R.id.ImageView, R.id.TextView});
        listView.setAdapter(SimpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data.add(map);
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), FourthActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), SecondActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(), ThirdActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                if (isLastLow) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("image", R.drawable.shouji);
                    map.put("text", "新增的项");
                    data.add(map);
                }
                SimpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount > 0) {
            isLastLow = true;
        } else {
            isLastLow = false;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag","被销毁了");
    }
}
