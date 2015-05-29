package com.bm.gaohua_framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description SharedPreferences工具类
 * @author 高骅
 * @date 2015-4-22 下午3:47:11
 */
public class SharedPreferencesUtil {
	private static SharedPreferences sp;
	private static Editor ed;
	/**
	 * @author 周祥浩
	 * @Description 缓存全局数据
	 * @return void
	 * @date 2014-12-9 下午4:23:41
	 */
	public static void cacheData(Context context,String key,String value){
		if(sp==null){
			sp=context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);	
		}
		if(ed==null){
			ed = sp.edit();	
		}
		ed.putString(key, value);
		ed.commit();
	}
	
	
	/**
	 * @author 周祥浩
	 * @Description 根据对于的key值获取对于的全局的值
	 * @return String
	 * @date 2014-12-9 下午4:30:31
	 */
	public static String getData(Context context,String key,String defValue){
		if(sp==null){
			sp=context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);	
		}
		return sp.getString(key, defValue);
	}
	
}
