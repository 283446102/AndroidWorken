package com.example.csu.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class duanxin extends AppCompatActivity {

    private List<Map<String,Object>> dataList;
    private String[] iconName = {"魅族16th", "小米8", "OPPO R17", "IPone X", "一加 6 plus", "中兴", "华为Mate20"};
    private int[] icon = {R.drawable.phone1, R.drawable.phone2, R.drawable.phone3, R.drawable.phone4, R.drawable.phone5, R.drawable.phone6, R.drawable.phone7};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duanxin);

        init();

    }

    private void init() {
        Spinner spinner = findViewById(R.id.spinner);

        dataList = new ArrayList<>();
        /**
         * 声明simpleAdapter适配器，将数据源于spinner下拉列表绑定
         */
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"image", "text"}, new int[]{R.id.ImageView, R.id.TextView});
        spinner.setAdapter(simpleAdapter);
    }

    private List<Map<String,Object>> getData(){
        for (int i =0;i<icon.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }
}
