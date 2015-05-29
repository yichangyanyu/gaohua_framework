package com.bm.gaohua_framework.activity;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.inject.InjectContentView;
import com.bm.gaohua_framework.inject.InjectUtils;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 注解测试
 * @author 高骅
 * @date 2015-3-17 下午6:46:18
 */
@InjectContentView(value = R.layout.act_inject_test)
public class InjectTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		InjectUtils.injectLayout(this);
	}

}
