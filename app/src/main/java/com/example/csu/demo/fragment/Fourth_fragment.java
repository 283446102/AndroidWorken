package com.example.csu.demo.fragment;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.csu.demo.R;
import com.example.csu.demo.adobeActivity;
import com.example.csu.demo.browser_Activity;
import com.example.csu.demo.duanxin;
import com.example.csu.demo.music_Activity;
import com.example.csu.demo.mytestActivity;
import com.example.csu.demo.viewPageDemo_Activity;
import com.example.csu.demo.ViewFlipperDemo_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fourth_fragment extends Fragment implements AdapterView.OnItemClickListener {
    NotificationManager notificationManager;
    View view;
    /**
     * 声明图标名字资源
     */
    private String[] iconName = {"下拉列表Demo", "进度条Demo", "webView Demo", "音乐(未完成)", "QQ底部导航栏",
            "viewPage Demo", "ViewFlipper Demo", "微信", "万能WiFi钥匙"};
    /**
     * 声明图标资源
     */
    private int[] icon = {R.drawable.duanxin, R.drawable.adobe, R.drawable.webbrowser,
            R.drawable.music, R.drawable.note,
            R.drawable.shangcheng, R.drawable.tiantianpitu,
            R.drawable.wechat, R.drawable.wifi};
    private List<Map<String, Object>> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth, container, false);
        init();
        return view;
    }

    private void init() {
        GridView gridView = view.findViewById(R.id.GridView);
        data = new ArrayList<>();
        //声明SimpleAdapter绑定于GridView
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.item2, new String[]{"image", "text"}, new int[]{R.id.Image1, R.id.text3});
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
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), duanxin.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), adobeActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity(), browser_Activity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getActivity(), music_Activity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(getActivity(), mytestActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(getActivity(), viewPageDemo_Activity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(getActivity(), ViewFlipperDemo_Activity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getActivity(), iconName[position] + "正在努力开发中···", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
