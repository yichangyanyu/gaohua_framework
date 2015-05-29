package com.bm.gaohua_framework.views.animview;

import com.bm.gaohua_framework.R;

import android.R.bool;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 带动画的imageview
 * @author 高骅
 * @date 2015-4-26 下午2:37:15
 */
public class AnimImageView extends ImageView {
	// 旋转动画
	private RotateAnimation rotateAnimation;
	//开关
	private boolean animSwitch=true;

	public AnimImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initRotateAnimation();
		setStartAnimationListener();
		this.setBackgroundResource(R.drawable.juhua);
	}

	public AnimImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AnimImageView(Context context) {
		super(context);
		initRotateAnimation();
		setStartAnimationListener();
		this.setBackgroundResource(R.drawable.juhua);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.chrysanthemumview
	 * @author 高骅
	 * @Description 初始化动画
	 * @return void
	 * @date 2015-4-26 下午3:14:35
	 */
	private void initRotateAnimation() {
		// pivotXValue pivotYValue 缩放中心坐标
		// fromDegree 起始旋转角度 此角度是当前为0及360,设置其他值则先跳至该角度的位置再由from - to的值:
		// 负则正向转,正则反向转
		// toDegrees 动画旋转到的角度
		// pivotXType 动画在X轴相对于物件位置类型
		// pivotXValue 动画相对于物件的X坐标的开始位置
		// 此值是以本身原始位置为原点,即如设为20%p,则向右移动父控件的20%位移,为负数则向左移
		// pivotYType 动画在Y轴相对于物件位置类型
		// pivotYValue为动画相对于物件的Y坐标的开始位置
		// 此值是以本身原始位置为原点,即如设为20%p,则向下移动父控件的20%位移,为负数则向上移
		rotateAnimation=new RotateAnimation(0, 360, Animation.ABSOLUTE, 50, Animation.ABSOLUTE, 50);
		rotateAnimation.setDuration(1000);
		//动画播放次数
		rotateAnimation.setRepeatCount(100000);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.chrysanthemumview
	 * @author 高骅
	 * @Description 开启动画
	 * @return void
	 * @date 2015-4-26 下午3:50:51
	 */
	private void startAnimation() {
		this.setAnimation(rotateAnimation);
		this.startAnimation(rotateAnimation);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.chrysanthemumview
	 * @author 高骅
	 * @Description 设置动画
	 * @return void
	 * @date 2015-4-26 下午3:12:04
	 */
	private void setStartAnimationListener() {
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!animSwitch) {
					//停止动画
					AnimImageView.this.clearAnimation();
					animSwitch=true;
					return;
				}
				startAnimation();
				animSwitch=false;
			}
		});
	}

}
