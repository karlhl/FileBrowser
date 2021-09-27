package com.example.showpathfiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showpathfiles.R;
import com.example.showpathfiles.entity.MyFile;

import java.util.ArrayList;
import java.util.List;

public class FileAdapter extends BaseAdapter {

    // 文件夹数据
    private List<MyFile> fdata = new ArrayList<MyFile>();
    // 上下文
    private Context context;

    public FileAdapter(List<MyFile> fdata, Context context) {
        this.fdata = fdata;
        this.context = context;
    }



    @Override
    public int getCount() {
        return fdata.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position; // 获取数据索引id
    }

    // 优化listview
    //定义一个viewholder静态类
    static class ViewHolder{
        // 定义属性，对应是列表项
        public ImageView myimg;
        public TextView myname;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 定义一个
        ViewHolder holder;
        // 判断convertView 是否为空，判断convertView对应的列表项
        if(convertView == null){
            // 新建
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.file,parent,false);

            holder.myimg = (ImageView) convertView.findViewById(R.id.imageView);
            holder.myname = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(holder);

        }else{
            //复用列表项
            holder = (ViewHolder)convertView.getTag();
        }
        //设置列表项数据
        holder.myimg.setImageResource(fdata.get(position).getImg());
        holder.myname.setText(fdata.get(position).getFilename());

        return convertView;


    }
}


































