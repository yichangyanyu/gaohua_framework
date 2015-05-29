package com.bm.gaohua_framework.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Prject XinJiangOrdering
 * @Package com.xinjianghordering.utits
 * @ClassName TheScreenAdaptation
 * @Description 屏幕适配工具类
 * @author 周祥浩
 * @ChangedBy zhouxianghao
 * @date 2015-4-8 上午11:23:49 
 */
public class ScreenUtil {
	private static ScreenUtil instants;
	private DisplayMetrics displayMetrics;

	/**
	 * @author 周祥浩
	 * @Description 单例TheScreenAdaptation
	 * @return
	 * @return TheScreenAdaptation
	 * @date 2015-4-8 上午11:25:17
	 */
	public static ScreenUtil getInstants() {
		if(instants==null){
			instants=new ScreenUtil();
		}
		return instants;
	}
	
	/**
	 * @author 周祥浩
	 * @Description 获取屏幕宽高
	 * @param content
	 * @param isGetWidth 判断是获取宽还是高
	 * @return
	 * @return int
	 * @date 2015-4-8 上午11:29:21
	 */
	public int getScreenWidthOrHeight(Context content,Boolean isGetWidth){
		if(displayMetrics==null){
			
			displayMetrics = content.getApplicationContext().getResources().getDisplayMetrics(); 
		}
		return isGetWidth?displayMetrics.widthPixels:displayMetrics.heightPixels;
		
	}
	
	/**
	 * @author 周祥浩
	 * @Description 获取横纵坐标dpi
	 * @param content
	 * @param isGetWidthDpi 判断是获取横向dpi还是纵向dpi
	 * @return
	 * @return float
	 * @date 2015-4-8 上午11:32:51
	 */
	public float getDpiForWidthOrHeight(Context content,Boolean isGetWidthDpi){
		if(displayMetrics==null){
			
			displayMetrics = content.getApplicationContext().getResources().getDisplayMetrics(); 
		}
		return isGetWidthDpi?displayMetrics.xdpi:displayMetrics.ydpi;
		
	}
	
	/**
	 * @author 周祥浩
	 * @Description 获取dpi
	 * @param content
	 * @return
	 * @return float
	 * @date 2015-4-8 上午11:35:06
	 */
	public float getDpi(Context content){
		if(displayMetrics==null){
			
			displayMetrics = content.getApplicationContext().getResources().getDisplayMetrics(); 
		}
		return displayMetrics.densityDpi;
		
	}
	/**
	 * @author 周祥浩
	 * @Description 获取控件宽高
	 * @param view
	 * @param isGetWidth 判断是获取控件宽还是高
	 * @return
	 * @return int
	 * @date 2015-4-8 上午11:40:17
	 */
	public int getWidgetWidthOrHeight(View view,Boolean isGetWidth){
		int width = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		int height = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
	    view.measure(width, height);  
		return isGetWidth?view.getMeasuredWidth():view.getMeasuredHeight();
		
	}
}
