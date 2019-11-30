package com.example.showscreenshowondialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.example.showscreenshowondialog.Adapter.PictureAdapter;
import com.example.showscreenshowondialog.FunctionClass.Config;
import com.example.showscreenshowondialog.FunctionClass.ShowImageDialog;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyStoragePermissions(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDeviceDensity();

        gridView=findViewById(R.id.gridView);
        String[] titles=getTitles();
        final String[] imagesPath=getImagesPath();
        for (int i=0;i<imagesPath.length;i++){
            Log.d(TAG, "onCreate: imagesPath["+i+"]"+"="+imagesPath[i]);
        }
        PictureAdapter pictureAdapter=new PictureAdapter(titles,imagesPath,MainActivity.this);
        gridView.setAdapter(pictureAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new ShowImageDialog(MainActivity.this,imagesPath,i).show();
            }
        });
    }

    protected void getDeviceDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Config.EXACT_SCREEN_HEIGHT = metrics.heightPixels;
        Config.EXACT_SCREEN_WIDTH = metrics.widthPixels;
    }


    protected String[] getImagesPath(){
        //修改此处的读取路径即可复用
        String[] titles=com.example.showscreenshowondialog.FunctionClass.FileUtil.getImageNames(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"+File.separator);
        String[] imagesPath=new String[titles.length];
        for (int i=0;i<titles.length;i++){
            imagesPath[i]=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"+File.separator+titles[i];
        }
        return imagesPath;
    }


    protected String[] getTitles(){
        //修改此处的读取路径即可复用
        String[] titles=com.example.showscreenshowondialog.FunctionClass.FileUtil.getImageNames(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"+File.separator);
        return titles;
    }


    //申请读写以及创建文件夹权限的方法，需在Activity启动时进行申请
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


}
