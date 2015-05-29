package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.List;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.MainAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.utils.ToastUtil;

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
 * @Description 相册模块
 * @author 高骅
 * @date 2015-5-8 下午6:31:27
 */
public class AboutPhotoActivity extends Activity implements IBaseActivity {
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
		setContentView(R.layout.act_about_photo);
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
					startActivity(new Intent(AboutPhotoActivity.this, ChoosePhotoActivity.class));
					break;
				case 1:
					ToastUtil.getInterface().showToast(AboutPhotoActivity.this, "制作中...", 1);
					break;
				case 2:
					startActivity(new Intent(AboutPhotoActivity.this, PhotoChoiceActivity.class));
					break;
				case 3:
					startActivity(new Intent(AboutPhotoActivity.this, PhotoViewActivity.class));
					break;
				case 4:
					startActivity(new Intent(AboutPhotoActivity.this, ImageCycleActivity.class));
					break;
				}
			}
		});
	}

	@Override
	public void initData() {
		data = new ArrayList<Object>();
		String menu1 = "系统自带剪裁的相机及相册";
		String memu2 = "第三方剪裁的相机及相册";
		String menu3 = "相册多选";
		String menu4 = "手势放大缩小图片（多用途）";
		String menu5 = "首页ImageCycleView轮播(广告轮播)";

		data.add(menu1);
		data.add(memu2);
		data.add(menu3);
		data.add(menu4);
		data.add(menu5);

	}

}
