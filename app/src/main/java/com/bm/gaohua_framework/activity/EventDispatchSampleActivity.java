package com.bm.gaohua_framework.activity;
import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description view事件分发实例
 * 大致结构:
 * ViewGroup和View组成了一棵树形结构，
 * 最顶层为Activity的ViewGroup，
 * 下面有若干的ViewGroup节点，
 * 每个节点之下又有若干的ViewGroup节点或者View节点
 * 当一个Touch事件(触摸事件为例)到达根节点，
 * 即Acitivty的ViewGroup时，它会依次下发，
 * 下发的过程是调用子View(ViewGroup)的dispatchTouchEvent方法实现的。
 * 简单来说，就是ViewGroup遍历它包含着的子View，
 * 调用每个View的dispatchTouchEvent方法，
 * 而当子View为ViewGroup时，
 * 又会通过调用ViwGroup的dispatchTouchEvent方法
 * 继续调用其内部的View的dispatchTouchEvent方法。
 * @author 高骅
 * @date 2015-3-11 下午2:04:06
 */
public class EventDispatchSampleActivity extends Activity implements IBaseActivity {
	private Button dispatchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_eventdispatchsample);
		initView();
	}

	@Override
	public void close(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @Description 如果View设置了onTouchListener了，
	 * 并且onTouch()方法返回true
	 * 则不执行View的onTouchEvent()方法
	 * 也表示View消费了Touch事件
	 * 返回false则继续执行onTouchEvent()
	 * @author 高骅
	 * @see com.bm.gaohua_framework.interfaces.IBaseActivity#initView()
	 */
	@Override
	public void initView() {
		dispatchBtn = (Button) this.findViewById(R.id.btn_dispatch);
		dispatchBtn.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();

				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.e("EventDispatchSampleActivity", "EventDispatchSampleActivity的onTouch轻抚了(DOWN)浩浩的菊花");
					break;

				case MotionEvent.ACTION_MOVE:
					Log.e("EventDispatchSampleActivity", "EventDispatchSampleActivity的onTouch在(MOVE)浩浩的小手上滑来滑去");
					break;

				case MotionEvent.ACTION_UP:
					Log.e("EventDispatchSampleActivity", "EventDispatchSampleActivity的onTouch依依不舍的松开了(UP)浩浩的小手");
					break;

				}
				return true;
			}
		});

	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
}
