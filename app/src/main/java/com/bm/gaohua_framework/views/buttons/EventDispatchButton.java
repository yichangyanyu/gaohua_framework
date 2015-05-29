package com.bm.gaohua_framework.views.buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 为了展示投递流程的button
 * @author 高骅
 * @date 2015-3-24 下午2:51:42
 */
public class EventDispatchButton extends Button {
	private Context context;

	public EventDispatchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

    /**
     * 
     * @Description 用来处理TouchEvent
     * @author 高骅
     * @see android.widget.TextView#onTouchEvent(android.view.MotionEvent)
     */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e("EventDispatchButton", "EventDispatchButton的onTouchEvent轻抚了(DOWN)浩浩的菊花");
			break;

		case MotionEvent.ACTION_MOVE:
			Log.e("EventDispatchButton", "EventDispatchButton的onTouchEvent在(MOVE)浩浩的小手上滑来滑去");
			break;

		case MotionEvent.ACTION_UP:
			Log.e("EventDispatchButton", "EventDispatchButton的onTouchEvent依依不舍的松开了(UP)浩浩的小手");
			break;

		}
		return super.onTouchEvent(event);
	}

	/**
	 * 
	 * @Description 用来分发TouchEvent
	 * 和ViewGroup的dispatchTouchEvent方法不同
	 * ViewGroup的dispatchTouchEvent是负责向子view分发事件
	 * 而View的dispatchTouchEvent方法，
	 * 并不执行分发工作，
	 * 或者说它分发的对象就是自己，
	 * 决定是否把touch事件交给自己处理，
	 * 而处理的方法，便是onTouchEvent事件
	 * 实际情况下
	 * 我们不该在普通View内重写dispatchTouchEvent方法，这里是只是展示流程
	 * 因为子view已经是最下层了（自上而下 viewgroup-view）它并不执行分发逻辑。当Touch事件到达View时，
	 * 我们该做的就是是否在onTouchEvent事件中处理它。
	 * @author 高骅
	 * @see android.view.View#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			Log.e("EventDispatchButton", "EventDispatchButton的dispatchTouchEvent轻抚了(DOWN)浩浩的菊花");
			break;

		case MotionEvent.ACTION_MOVE:
			Log.e("EventDispatchButton", "EventDispatchButton的dispatchTouchEvent在(MOVE)浩浩的小手上滑来滑去");
			break;

		case MotionEvent.ACTION_UP:
			Log.e("EventDispatchButton", "EventDispatchButton的dispatchTouchEvent依依不舍的松开了(UP)浩浩的小手");
			break;

		}
		return super.dispatchTouchEvent(event);
		//return true;
	}

}
