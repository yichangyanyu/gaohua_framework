package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.List;

import com.bm.gaohua_framework.adapter.PhotoViewPagerAdapter;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.views.viewpagers.HackyViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description 带手势放大缩小的viewpager
 * @author 高骅
 * @date 2015-4-22 下午5:04:38
 */
public class PhotoViewActivity extends Activity implements IBaseActivity{
	private ViewPager mViewPager;
	private PhotoViewPagerAdapter mPhotoViewPagerAdapter;
	// 图片路径
	private List<String> photoUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initData();
		initView();
	
	}

	@Override
	public void close(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initView() {
		mViewPager=new HackyViewPager(this);
		mPhotoViewPagerAdapter=new PhotoViewPagerAdapter(this, photoUrl);
		setContentView(mViewPager);
		mViewPager.setAdapter(mPhotoViewPagerAdapter);

	}

	@Override
	public void initData() {
		photoUrl=new ArrayList<String>();
        photoUrl.add("http://img3.tgbusdata.cn/v2/thumb/jpg/NDE2Qiw1ODAsMTAwLDQsMywxLC0xLDAscms1MCw2MS4xNTIuMjQyLjE=/u/psv.tgbus.com/UploadFiles_5352/201404/20140425160218127.jpg");
        photoUrl.add("http://p4.music.126.net/KmxWsxomyJ5iZUMKuUFtVg==/5914273046100718.jpg");
	    photoUrl.add("http://img.funshion.com/pictures/320/818/8/3208188.jpg");
	    photoUrl.add("http://img.wanyx.com/upload/201407/03/1404380811.jpg");
	    photoUrl.add("http://attachment1.tgbusdata.cn/jpg/27/f8/dfc2ada8497a901df9b93ff700a5_bec5a3981a49e7422997b03dbd1d59ea265f54cad09a07551bafd680a32c10e23af06b3237d9c815.jpg");
	}

}
