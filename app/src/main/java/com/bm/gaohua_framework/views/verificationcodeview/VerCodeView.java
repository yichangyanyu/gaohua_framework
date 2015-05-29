package com.bm.gaohua_framework.views.verificationcodeview;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.bm.gaohua_framework.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 验证码View
 * @author 高骅
 * @date 2015-4-26 下午4:30:17
 */
public class VerCodeView extends View {
	/**
	 * 2015/4/26  坑点 坑出一脸血 
	 * 自定义view特别注意:
	 * 1.styleable的name取名如果和View名字一样的话，在写布局文件的时候，自定义属性才能有代码提示。
	 * 2.一定要引入自己的命名空间  xmlns:custom="http://schemas.android.com/apk/res/包名"，
	 *   后面的包路径指的是项目的package
	 * 3.构造函数问题：自定义一个View，必须派生实现基类View的三个构造函数
	 *   否则也会报错
	 *   这三个构造函数是用来在不同的应用场合来实例化一个View对象
	 *   比如XML引用方式。
	 */

	// 文本内容
	private String mText;
	// 文字颜色
	private int mTextColor;
	// 文字大小
	private int mTextSize;
	// 绘制时控制文本绘制的范围
	private Rect mBound;
	// 画笔
	private Paint mPaint;

	public VerCodeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VerCodeView(Context context) {
		this(context, null);
	}

	/**
	 * 
	 * @Description 获取自定义属性
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public VerCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initVerCodeViewOption(context, attrs, defStyleAttr);
		initPaint();
		initListener();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = 0;
		int height = 0;
		width = setWidth(widthMeasureSpec, width);
		height = setHeight(heightMeasureSpec, height);
		setMeasuredDimension(width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
		mPaint.setColor(mTextColor);
		canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 设置宽度
	 * @return int
	 * @date 2015-4-26 下午5:28:48
	 */
	private int setWidth(int widthMeasureSpec, int width) {
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);

		switch (specMode) {
		case MeasureSpec.EXACTLY:
			width = getPaddingLeft() + getPaddingRight() + specSize;
			break;

		case MeasureSpec.AT_MOST:
			width = getPaddingLeft() + getPaddingRight() + mBound.width();
			break;
		}
		return width;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 设置高度
	 * @return int
	 * @date 2015-4-26 下午5:28:02
	 */
	private int setHeight(int heightMeasureSpec, int height) {
		int specMode;
		int specSize;
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		switch (specMode) {
		case MeasureSpec.EXACTLY:
			height = getPaddingTop() + getPaddingBottom() + specSize;
			break;
		case MeasureSpec.AT_MOST:
			height = getPaddingTop() + getPaddingBottom() + mBound.height();
			break;
		}
		return height;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 初始化点击事件
	 * @return void
	 * @date 2015-4-26 下午5:16:55
	 */
	private void initListener() {
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mText = randomText();
				// 获取到验证码重绘组件
				postInvalidate();
				requestLayout();
			}
		});
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 初始化画笔
	 * @return void
	 * @date 2015-4-26 下午5:15:56
	 */
	private void initPaint() {
		mPaint = new Paint();
		mPaint.setTextSize(mTextSize);
		mPaint.setColor(mTextColor);
		mBound = new Rect();
		mPaint.getTextBounds(mText, 0, mText.length(), mBound);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 获取XML里设置的属性
	 * @return void
	 * @date 2015-4-26 下午5:10:24
	 */
	private void initVerCodeViewOption(Context context, AttributeSet attrs, int defStyleAttr) {
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerCodeView, defStyleAttr, 0);
		int count = typedArray.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.VerCodeView_titleText:
				mText = typedArray.getString(attr);
				break;

			case R.styleable.VerCodeView_titleTextColor:
				// 初始设置为黑色
				mTextColor = typedArray.getColor(attr, Color.BLACK);
				break;

			case R.styleable.VerCodeView_titleTextSize:
				// 初始设置为16sp，TypeValue可以把sp转化为px
				mTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;
			}
		}
		// 用完回收
		typedArray.recycle();
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.views.verificationcodeview
	 * @author 高骅
	 * @Description 生成验证码
	 * @return String
	 * @date 2015-4-26 下午5:13:44
	 */
	private String randomText() {
		Random random = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < 4) {
			int randomInt = random.nextInt(10);
			set.add(randomInt);
		}
		StringBuffer sb = new StringBuffer();
		for (Integer i : set) {
			sb.append("" + i);
		}

		return sb.toString();
	}

}
