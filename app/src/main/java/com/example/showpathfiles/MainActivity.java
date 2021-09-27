package com.example.showpathfiles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.showpathfiles.adapter.FileAdapter;
import com.example.showpathfiles.entity.MyFile;
import com.example.showpathfiles.utils.DeleteFileUtil;
import com.example.showpathfiles.utils.OperateFileUtil;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "MainAcitvity";

    // 初始路径
    //String directoryPath = "/storage/1016-3A07/DCIM"; // 模拟器
    String directoryPath = "/storage/1016-3A07"; // sdk 30 模拟器
    //String directoryPath = "/storage/emulated/0"; // mi10

    private ListView mylistview; // 显示列表内容
    private FileAdapter fileAdapter;
    private Toolbar mToolbar;
    private TextView curPath; // 显示当前路径
    ArrayList<File> files; // 显示文件夹内所有文件
    Stack<String> pathStack = new Stack<>(); // 路径栈
    private Context mContext;



    // 定义一个通讯录列表，作数据源
    private List<MyFile> datas = new ArrayList<MyFile>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置不显示title
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置toolbar
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        setContentView(R.layout.activity_main);

        // 路径栈
        pathStack.push(directoryPath);
        Log.e(TAG, String.valueOf(pathStack));

        // 初始化
        initView();

        // 显示所有路径主函数
        showNewPathRoot(directoryPath);
    }

    private void initView() {
        curPath = (TextView) findViewById(R.id.curPath);

    }

    // 显示所有路径主函数
    private void showNewPathRoot(String directoryPath) {
        // 显示当前路径，就是显示栈的最后一条信息
        curPath.setText(pathStack.peek());

        files = directory2list(directoryPath);

        // 使用ListView展示通讯录
        //初始化空间
        mylistview = findViewById(R.id.mylistview);
        // 初始化数据
        initDatas();
        // 创建适配器（上下文，列表项布局文件，数据源）
        fileAdapter = new FileAdapter(datas, MainActivity.this);
        // 设置适配器到ListView
        mylistview.setAdapter(fileAdapter);
        // 监听点击事件
        mylistview.setOnItemClickListener(this); //单机某个条目的监听
        // 监听长按事件
        //mylistview.setOnItemLongClickListener(this); //单机某个条目的监听
        // 设置上下文菜单
        mylistview.setOnCreateContextMenuListener(this); //跟长按事件冲突

    }

    // 实现点击方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取点击条目对象
        MyFile mf = datas.get(position);

        if (mf.isDir()) {
            // 如果是文件夹
            pathStack.push(mf.getFilePath());
            Log.e(TAG, String.valueOf(pathStack));

            datas.removeAll(datas);
            fileAdapter.notifyDataSetChanged();
            mylistview.setAdapter(fileAdapter);
            showNewPathRoot(mf.getFilePath());
        } else {
            // 如果点击的是文件,弹出点击文件的信息
            Intent intent = OperateFileUtil.openPicByIntent(mContext,mf.getFilePath());
            startActivity(intent);

            //Toast.makeText(this, "您点击了第" + (position + 1) + "个文件，文件名：" + mf.getFilename(), Toast.LENGTH_SHORT).show();
        }

    }

    // 实现长按方法
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取点击条目对象
        MyFile mf = datas.get(position);
        // 长按function
        Toast.makeText(this, "您长按了第" + (position + 1) + "个文件，文件夹名称" + mf.getFilename(), Toast.LENGTH_SHORT).show();
        return false;
    }

    //创建ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.mylistview) {
            MenuInflater inflater = getMenuInflater();
            //menu.setHeaderTitle("contextView sample").setHeaderIcon(R.mipmap.ic_launcher);
            inflater.inflate(R.menu.select_file_menu, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getMenuInfo() instanceof AdapterView.AdapterContextMenuInfo) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            //处理菜单的点击事件
            switch (item.getItemId()) {
                case R.id.sendItem:
                    //mTextView.setText(item.getTitle().toString() + ":" +menuInfo.position);
                    break;
                case R.id.delectItem:
                    //mTextView.setText(item.getTitle().toString() + ":" +menuInfo.position);
                    Boolean flag = DeleteFileUtil.delete(datas.get(menuInfo.position).getFilePath());

                    String delect_file_name = datas.get(menuInfo.position).getFilename();
                    if(flag){
                        // 刷新ui
                        datas.removeAll(datas);
                        fileAdapter.notifyDataSetChanged();
                        mylistview.setAdapter(fileAdapter);
                        showNewPathRoot(pathStack.peek());
                        Toast.makeText(this,delect_file_name+"删除成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,delect_file_name+"删除失败",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.testItem :
                    //mTextView.setText(item.getTitle().toString() + ":" +menuInfo.position);
                    //Toast.makeText(this, "菜单名：" + item.getTitle().toString() + ";菜单位置:" + menuInfo.position, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "该文件名称:" + datas.get(menuInfo.position).getFilename(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        return super.onContextItemSelected(item);
    }


    public ArrayList<File> directory2list(String directoryPath) {
        /**
         * 将路径下所有文件返回一个列表，注意返回类型是File[]
         */
        File dire = new File(directoryPath);
        File[] files = dire.listFiles();
        ArrayList<File> f = new ArrayList<>();
        // 修改了没有权限获取到空文件导致的闪退
        if (files == null){
            Toast.makeText(MainActivity.this, "此文件夹没有权限访问", Toast.LENGTH_SHORT).show();
            return f;
        }
        for (File file : files) {
            f.add(file);
        }
        return f;
    }


    public void initDatas() {
        /**
         * 初始化数据，把数据放到datas里
         */
        for (File file : files) {
            MyFile mf = new MyFile(file.getName());
            mf.setFilePath(file.getPath());
            if (file.isDirectory()) {
                mf.setDir(true);
                mf.setImg(R.mipmap.dir);
            } else {
                mf.setDir(false);
                mf.setImg(R.mipmap.file);
            }
            // 放入数据
            datas.add(mf);
        }

    }

    // 重写返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown()");

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && pathStack.size() > 1) {
            String t = pathStack.pop();
            String curPath = pathStack.peek();

            Log.e(TAG, String.valueOf(pathStack));
            datas.removeAll(datas);
            fileAdapter.notifyDataSetChanged();
            mylistview.setAdapter(fileAdapter);
            showNewPathRoot(curPath);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}