package com.example.csu.demo.useless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.csu.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private boolean isLastLow=false;
    private SimpleAdapter SimpleAdapter;
    private ListView listView;
    private List<Map<String, Object>> data;
    private String[] iconName = {"我的桌面", "我的日历", "当前时间", "IPone X", "一加 6 plus", "中兴", "华为Mate20"};
    private int[] icon = {R.drawable.desk, R.drawable.calendar, R.drawable.clock, R.drawable.phone4, R.drawable.phone5, R.drawable.phone6, R.drawable.phone7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        data = new ArrayList<>();
        listView = findViewById(R.id.ListView);
        SimpleAdapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image", "text"}, new int[]{R.id.ImageView, R.id.TextView});
        listView.setAdapter(SimpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                if (isLastLow){
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
        if (firstVisibleItem+visibleItemCount>=totalItemCount&&totalItemCount>0){
            isLastLow=true;
        }else {
            isLastLow=false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(MainActivity.this, FourthActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ThirdActivity.class);
                startActivity(intent);
                break;
        }
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
}
