package com.bm.gaohua_framework.activity;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * 
 * Copyright © 2014 高骅. All rights reserved.
 * 
 * @Description 启动页
 * @author 高骅
 * @date 2014-11-23 上午3:03:32
 */
public class StartActivity extends Activity implements IBaseActivity {
	private ImageView iv_start;
	int alpha = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		initAnimation();
	}

	@Override
	public void close(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		setContentView(R.layout.act_startpage);
		iv_start = (ImageView) this.findViewById(R.id.iv_start);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	public void initAnimation() {
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(3000);
		iv_start.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				Intent intent = new Intent();
				intent.setClass(StartActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}
}
