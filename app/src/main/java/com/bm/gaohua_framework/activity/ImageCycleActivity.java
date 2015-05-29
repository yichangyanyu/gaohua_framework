package com.bm.gaohua_framework.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.app.App;
import com.bm.gaohua_framework.views.imagecycleview.ImageCycleView;
import com.bm.gaohua_framework.views.imagecycleview.ImageCycleView.ImageCycleViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description 广告轮播图
 * @author 高骅
 * @date 2015-4-26 下午2:12:59
 */
public class ImageCycleActivity extends Activity {

	private ImageCycleView mAdView;

	private ArrayList<String> mImageUrl = null;

	private String imageUrl1 = "http://img3.tgbusdata.cn/v2/thumb/jpg/NDE2Qiw1ODAsMTAwLDQsMywxLC0xLDAscms1MCw2MS4xNTIuMjQyLjE=/u/psv.tgbus.com/UploadFiles_5352/201404/20140425160218127.jpg";

	private String imageUrl2 = "http://img.funshion.com/pictures/320/818/8/3208188.jpg";

	private String imageUrl3 = "http://img.wanyx.com/upload/201407/03/1404380811.jpg";
	
	private String imageUrl4 = "http://attachment1.tgbusdata.cn/jpg/27/f8/dfc2ada8497a901df9b93ff700a5_bec5a3981a49e7422997b03dbd1d59ea265f54cad09a07551bafd680a32c10e23af06b3237d9c815.jpg";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_ad_cycle);
		mImageUrl = new ArrayList<String>();
		mImageUrl.add(imageUrl1);
		mImageUrl.add(imageUrl2);
		mImageUrl.add(imageUrl3);
		mImageUrl.add(imageUrl4);
		mAdView = (ImageCycleView) findViewById(R.id.ad_view);
		mAdView.setIsManualLoop(true);
		mAdView.setImageResources(mImageUrl, mAdCycleViewListener);
		
		
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(int position, View imageView) {
			// TODO 单击图片处理事件
			Toast.makeText(ImageCycleActivity.this, "position->"+position, 0).show();
		}

		@Override
		public void displayImage(String imageURL, ImageView imageView) {
			ImageLoader.getInstance().displayImage(imageURL, imageView, App.getInstance().getOptions());
		   
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		mAdView.startImageCycle();
	};

	@Override
	protected void onPause() {
		super.onPause();
		mAdView.pushImageCycle();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mAdView.pushImageCycle();
	}

}
