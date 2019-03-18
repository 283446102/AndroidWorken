package com.example.csu.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csu.demo.R;

import java.util.List;

public class zuiyouAdapter extends BaseAdapter {
    private List<ItemBean> mList;//数据源
    private LayoutInflater mInflater;//布局装载器

    public zuiyouAdapter(Context context,List<ItemBean> list){
        mList=list;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    //listView需要显示的数据量
    public int getCount() {
        return mList.size();
    }

    @Override
    //指定索引对应的数据项
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    //指定的索引的数据项ID
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            //将布局文件转化为view对象
            convertView =mInflater.inflate(R.layout.item,null);
            //绑定id
            viewHolder.imageView = convertView.findViewById(R.id.ImageView);
            viewHolder.content= convertView.findViewById(R.id.TextView);

            //通过setTag将convertView与viewHolder进行关联
            convertView.setTag(viewHolder);
        }else {//如果缓存池中有view缓存，则通过getTag取出viewHolder
            viewHolder =(ViewHolder) convertView.getTag();
        }

        ItemBean bean =mList.get(position);
        //设置控件数据
        viewHolder.imageView.setImageResource(bean.itemImageResId);
        viewHolder.content.setText(bean.itemContent);

        return convertView;
    }
}
