package com.bm.gaohua_framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.MainAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * TEXTVIE相关模块
 */
public class AboutTextViewActivity extends Activity implements IBaseActivity {
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
        setContentView(R.layout.act_about_text_view);
        initData();
        initView();
    }


    @Override
    public void close(View v) {
        this.finish();
    }

    @Override
    public void initView() {
        lv_main = (ListView) this.findViewById(R.id.lv_main);
        mainAdapter = new MainAdapter(this, data);
        lv_main.setAdapter(mainAdapter);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(AboutTextViewActivity.this, ExtendsTextViewActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        data = new ArrayList<Object>();
        String menu1 = "带展开收起效果的TextView";
        data.add(menu1);
    }
}
