package com.bm.gaohua_framework.views.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description 解决scrollview嵌套viewpage滑动冲突的问题
 * @author 高骅
 * @date 2015-4-2 下午5:42:15
 */
public class SlidingConflictScrollView extends ScrollView {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;

	public SlidingConflictScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {
				return false;
			}
		}

		return super.onInterceptTouchEvent(ev);
	}
	
	

}
