package com.bm.gaohua_framework.views.viewpagers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;


/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description 解决手势和滑动冲突的viewpager
 * @author 高骅
 * @date 2015-4-22 下午5:19:58
 */
public class HackyViewPager extends ViewPager {

	public HackyViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

}
