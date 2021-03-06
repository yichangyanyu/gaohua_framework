package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.List;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.MainAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 自定义View模块
 * @author 高骅
 * @date 2015-5-8 下午6:47:30
 */
public class AboutCustomViewActivity extends Activity implements IBaseActivity {
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
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_about_custom_view);
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
		lv_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(AboutCustomViewActivity.this, AnimationDemoActivity.class));
					break;
				case 1:
					startActivity(new Intent(AboutCustomViewActivity.this, VerCodeActivity.class));
					break;
				}
			}
		});
	}

	@Override
	public void initData() {
		data = new ArrayList<Object>();
		String memu1 = "带动画效果的自定义view";
		String memu2 = "自定义验证码view";

		data.add(memu1);
		data.add(memu2);

	}

}
