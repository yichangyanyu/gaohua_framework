package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.fragment.IndicatorFragment;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.utils.ScreenUtil;
import com.bm.gaohua_framework.utils.ToastUtil;
import com.bm.gaohua_framework.views.LazyViewPager;
import com.bm.gaohua_framework.views.LazyViewPager.OnPageChangeListener;


/**
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Prject Gh
 * @Package com.bm.gaohua_framework.activity
 * @ClassName IndicatorActivity
 * @Description 指示器demo
 * @author 周祥浩
 * @ChangedBy zhouxianghao
 * @date 2015-5-20 上午12:29:04 
 */
public class IndicatorActivity extends FragmentActivity implements IBaseActivity, OnClickListener, OnPageChangeListener{
	
	private HorizontalScrollView hsv;
	private LinearLayout ll_title;
	private LazyViewPager lvp;
	private List<Object> data = new ArrayList<Object>();
	//title集合
	private List<TextView> tvList = new ArrayList<TextView>();
	//title和指示器的父控件集合
	private List<RelativeLayout> rlList=new ArrayList<RelativeLayout>();
	//指示器集合
	private List<ImageView> ivList=new ArrayList<ImageView>();
	private TabPageIndicatorAdapter fragmentPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_indicator);
		initView();
		initData();
	}

	@Override
	public void close(View v) {
		this.finish();
	}

	@Override
	public void initView() {
		//横向ScrollView
		hsv = (HorizontalScrollView) this.findViewById(R.id.hsv);
		//顶部分类的父控件	
		ll_title = (LinearLayout) this.findViewById(R.id.ll_title);
		//每一个分类的具体内容		
		lvp = (LazyViewPager) this.findViewById(R.id.lvp);
		
	}

	@Override
	public void initData() {
		//模拟服务器返回数据
		data.add("武汉");
		data.add("上海");
		data.add("北京");
		data.add("广州");
		data.add("深圳");
		data.add("天津");
		data.add("长沙");
		data.add("江西");
		data.add("成都");
		//初始化分类
		initTitle();
		//设置第一条选中
		setSelector(0);
		if(fragmentPagerAdapter==null){
			fragmentPagerAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
		}
		lvp.setAdapter(fragmentPagerAdapter);
		//设置viewpage的page改变事件
		lvp.setOnPageChangeListener(this);
	}
	/**
	 * @author 周祥浩
	 * @Description 初始化头部布局
	 * @return void
	 * @date 2015-3-15 上午12:29:53
	 */
	private void initTitle() {
		//根据网络获取数据动态添加多少个子view
		for (int i = 0; i < data.size(); i++) {
			RelativeLayout rl=(RelativeLayout) View.inflate(getApplicationContext(), R.layout.indicator_item, null);			
			TextView tv=(TextView) rl.findViewById(R.id.tv_title);
			ImageView iv=(ImageView) rl.findViewById(R.id.iv);
			//初始化时第一条指示器可见
			if(i==0){
				iv.setVisibility(View.VISIBLE);
			}else{
				iv.setVisibility(View.GONE);
			}
			//设置textview的属性以及值
			tv.setText(data.get(i).toString());
			tv.setId(i);
			tv.setTextSize(14);
			tv.setTextColor(Color.WHITE);			
			tv.setGravity(Gravity.CENTER);
			//给子view设置标示以及点击事件
			rl.setId(i);
			rl.setOnClickListener(this);
			//分别把各自的控件添加到相应的集合去
			tvList.add(tv);
			ivList.add(iv);
			rlList.add(rl);
			ll_title.addView(rl);
			//设置每个子view的宽度
			LinearLayout.LayoutParams mLayoutParams= (LayoutParams) rl.getLayoutParams();
			mLayoutParams.width=ScreenUtil.getInstants().getScreenWidthOrHeight(getApplicationContext(), true)/4;
			rl.setLayoutParams(mLayoutParams);

		}
	}

	@Override
	public void onClick(View v) {
		setSelector(v.getId());
		
	}
	/**
	 * @author 周祥浩
	 * @Description 设置选择
	 * @param id
	 * @return void
	 * @date 2015-3-15 上午12:56:14
	 */
	public void setSelector(int id) {
		for (int i = 0; i < data.size(); i++) {
			if (id == i) {
				ivList.get(i).setVisibility(View.VISIBLE);
				tvList.get(id).setTextColor(Color.BLACK);
				if (i > 2) {
					hsv.smoothScrollTo((rlList.get(i).getWidth() * i ), 0);
				} else {
					hsv.smoothScrollTo(0, 0);
				}
				lvp.setCurrentItem(i);
			} else {
				ivList.get(i).setVisibility(View.GONE);
				tvList.get(i).setTextColor(Color.WHITE);
			}
		}
	}
	class TabPageIndicatorAdapter extends FragmentPagerAdapter {
		public TabPageIndicatorAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// 新建一个Fragment来展示ViewPager item的内容，并传递参数
			Fragment fragment = new IndicatorFragment();
			Bundle args = new Bundle();
			args.putString("title", (data.get(position)).toString());
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return data.size();
		}
	}
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		//当page改变从新设置选择位置
		setSelector(position);
		ToastUtil.getInterface().showToast(getApplicationContext(), "position="+position, 0);
		
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}
}
