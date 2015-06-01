package com.bm.gaohua_framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.MainAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2015 蓝色互动. All rights reserved.
 *
 * @author 高骅
 * @Description 主页
 * @date 2015-3-19 下午8:03:45
 */
public class MainActivity extends Activity implements IBaseActivity {
    /**
     * 主页数据
     */
    private List<Object> data;

    /**
     * 主页适配器
     */
    private MainAdapter mainAdapter;

    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initData();
        initView();
    }

    @Override
    public void close(View v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initView() {
        lv_main = (ListView) this.findViewById(R.id.lv_main);
        mainAdapter = new MainAdapter(this, data);
        lv_main.setAdapter(mainAdapter);
        lv_main.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        // 相册模块Demo
                        startActivity(new Intent(MainActivity.this, AboutPhotoActivity.class));
                        break;
                    case 1:
                        // 自定义ViewDemo
                        startActivity(new Intent(MainActivity.this, AboutCustomViewActivity.class));
                        break;
                    case 2:
                        // 基础教程Demo
                        startActivity(new Intent(MainActivity.this, AboutBaseCourseActivity.class));
                        break;
                    case 3:
                        // 常用模块Demo
                        startActivity(new Intent(MainActivity.this, AboutCommonFunctionActivity.class));
                        break;
                    case 4:
                        // 常用动画模块Demo
                        startActivity(new Intent(MainActivity.this, AboutCommonAnimationActivity.class));
                        break;
                    case 5:
                        // ListView相关Demo
                        ToastUtil.getInterface().showToast(MainActivity.this, "建设中..", 1);
                        break;
                    case 6:
                        // GIF图播放
                        startActivity(new Intent(MainActivity.this, AboutImageViewActivity.class));
                        break;
                    case 7:
                        // TextView相关demo
                        startActivity(new Intent(MainActivity.this, AboutTextViewActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        data = new ArrayList<Object>();
        String menu1 = "相册相关Demo";
        String menu2 = "自定义View相关Demo";
        String menu3 = "基础教程相关Demo";
        String menu4 = "常用模块Demo";
        String menu5 = "常用动画Demo";
        String menu6 = "ListView相关Demo";
        String menu7 = "ImageView相关Demo";
        String menu8 = "TextView相关Demo";
        data.add(menu1);
        data.add(menu2);
        data.add(menu3);
        data.add(menu4);
        data.add(menu5);
        data.add(menu6);
        data.add(menu7);
        data.add(menu8);

    }
}
