package com.example.csu.demo.useless;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.csu.demo.R;
import com.example.csu.demo.adobeActivity;
import com.example.csu.demo.browser_Activity;
import com.example.csu.demo.duanxin;
import com.example.csu.demo.music_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourthActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] iconName = {"短信", "进度条Demo", "火箭浏览器", "音乐", "便签",
            "商城", "天天p图", "微信", "万能WiFi钥匙"};
    private int[] icon = {R.drawable.duanxin, R.drawable.adobe, R.drawable.webbrowser,
            R.drawable.music, R.drawable.note,
            R.drawable.shangcheng, R.drawable.tiantianpitu,
            R.drawable.wechat, R.drawable.wifi};
    private SimpleAdapter simpleAdapter;
    private GridView gridView;
    private List<Map<String, Object>> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);
        init();

    }

    private void init() {

        gridView = findViewById(R.id.GridView);

/*        Drawable drawable_first = getResources().getDrawable(R.drawable.first_icon);
        drawable_first.setBounds(0,0,75,75);
        radioButton_first.setCompoundDrawables(null,drawable_first,null,null);

        Drawable drawable_second = getResources().getDrawable(R.drawable.second_icon);
        drawable_second.setBounds(0,0,75,75);
        radioButton_second.setCompoundDrawables(null,drawable_second,null,null);

        Drawable drawable_third = getResources().getDrawable(R.drawable.third_icon);
        drawable_third.setBounds(0,0,75,75);
        radioButton_third.setCompoundDrawables(null,drawable_third,null,null);

        Drawable drawable_fourth = getResources().getDrawable(R.drawable.fourth_icon);
        drawable_fourth.setBounds(0,0,75,75);
        radioButton_fourth.setCompoundDrawables(null,drawable_fourth,null,null);*/

        data = new ArrayList<>();
        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.item2, new String[]{"image", "text"}, new int[]{R.id.Image1, R.id.text3});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
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
        Toast.makeText(this, "我是" + iconName[position], Toast.LENGTH_SHORT).show();
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(this, duanxin.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, adobeActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, browser_Activity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, music_Activity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent();
                intent.setClassName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                startActivity(intent);
                break;
        }
    }
}

