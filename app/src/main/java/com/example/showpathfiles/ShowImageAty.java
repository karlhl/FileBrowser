package com.example.showpathfiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImageAty extends AppCompatActivity {

    ImageView ivQiaoba;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_aty);
        ivQiaoba = (ImageView) findViewById(R.id.imageView2);
        Intent getImage = getIntent();
        //不为空判断
        if (getImage != null) {
            //获取intent传递过来的uri数据
            Uri data = getImage.getData();
            if (data != null) {
                ivQiaoba.setImageURI(data);
            }
        }


    }
}