package com.example.showpathfiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class OpenPicActivity extends AppCompatActivity {
    public String TAG = "OpenPicActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pic);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //要是自定义应用打开图片必须添加ACTION_VIEW的Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //进行图片-->bitmap-->uri转换
                String url = "/storage/1016-3A07/DCIM/DJI_202107260905_016/DJI_20210728105227_0010_Z.JPG";
                File file = new File(url);
                Uri uri;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    uri = Uri.fromFile(file);
                    Log.e(TAG,uri.toString());
                } else {
                    uri = FileProvider.getUriForFile(mContext,"com.example.showpathfiles.provider", file);
                    Log.e(TAG,uri.toString());
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                //设置数据和类型可以用setData( )或setType( ) ,但是同时设置的话是不生效的，
                // 只能使用setDataAndType( ) ,image代表图片，星号代表图片中所有格式，
                // 可根据自己需要筛选，如只想打开jpg类型图片可用“image/jpg”
                intent.setDataAndType(uri,"image/*");
                startActivity(intent);
            }
        });


    }
}