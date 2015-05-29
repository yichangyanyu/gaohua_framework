package com.bm.gaohua_framework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 验证码
 * @author 高骅
 * @date 2015-4-26 下午5:31:43
 */
public class VerCodeActivity extends Activity implements IBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        initView();
	}

	@Override
	public void close(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		setContentView(R.layout.act_vercode);

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
