package com.bm.gaohua_framework.interfaces;

import android.view.View;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 通用接口
 * @author 高骅
 * @date 2015-3-12 下午12:17:14
 */
public interface IBaseActivity {
	/**
	 * 
	 * @author 高骅
	 * @Description 关闭
	 * @return void
	 * @date 2015-3-12 下午12:04:30
	 */
	public void close(View v);

	/**
	 * 
	 * @author 高骅
	 * @Description 初始化视图
	 * @return void
	 * @date 2015-3-12 下午12:05:40
	 */
	public void initView();

	/**
	 * 
	 * @Package com.bm.gaohua_framework.interfaces
	 * @author 高骅
	 * @Description 初始化数据
	 * @return void
	 * @date 2015-3-19 下午8:02:10
	 */
	public void initData();



}
