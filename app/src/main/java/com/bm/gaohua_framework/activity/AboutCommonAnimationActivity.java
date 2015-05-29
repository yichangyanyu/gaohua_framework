package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.MainAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 常用动画模块
 * @author 高骅
 * @date 2015-5-8 下午7:43:29
 */
public class AboutCommonAnimationActivity extends Activity implements IBaseActivity {
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
		setContentView(R.layout.act_common_animation);
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
					// 动画模块Demo
					startActivity(new Intent(AboutCommonAnimationActivity.this, RadiationActivity.class));
					break;
				}
			}
		});
	}

	@Override
	public void initData() {
		data = new ArrayList<Object>();
		String menu1 = "波纹扫描Demo";
		data.add(menu1);

	}

}
