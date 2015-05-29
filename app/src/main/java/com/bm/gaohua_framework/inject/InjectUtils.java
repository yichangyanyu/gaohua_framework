package com.bm.gaohua_framework.inject;

import java.lang.reflect.Method;

import android.app.Activity;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 注解工具
 * @author 高骅
 * @date 2015-3-15 下午6:25:39
 */
public class InjectUtils {

	/**
	 * 
	 * @Package com.gaohua_libs.inject
	 * @author 高骅
	 * @Description 注入布局
	 * @return void
	 * @date 2015-3-15 下午6:36:03
	 */
	public static void injectLayout(Activity activity) {

		injectContentView(activity);

	}

	/**
	 * 
	 * @Package com.gaohua_libs.inject
	 * @author 高骅
	 * @Description 注解布局
	 * @return void
	 * @date 2015-3-15 下午6:26:11
	 */
	private static void injectContentView(Activity activity) {
		/**
		 * 反射获取.class
		 */
		Class<? extends Activity> clazz = activity.getClass();
		/**
		 * 查询类上是否存在ContentView注解
		 */
		InjectContentView contentView = clazz.getAnnotation(InjectContentView.class);
		/**
		 * 如果存在
		 */
		if (contentView != null) {
			/**
			 * 得到layout的id
			 */
			int contentViewLayoutId = contentView.value();
			try {
				/**
				 * 通过反射获取方法
				 */
				Method method = clazz.getMethod("setContentView", int.class);
				method.setAccessible(true);
				method.invoke(activity, contentViewLayoutId);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
