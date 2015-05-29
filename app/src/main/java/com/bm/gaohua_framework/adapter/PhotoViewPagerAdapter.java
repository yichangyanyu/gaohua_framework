package com.bm.gaohua_framework.adapter;

import java.util.List;

import com.bm.gaohua_framework.app.App;
import com.bm.gaohua_framework.views.photoview.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description photoview适配器
 * @author 高骅
 * @date 2015-4-22 下午5:06:23
 */
public class PhotoViewPagerAdapter extends PagerAdapter {
	// 图片路径
	private List<String> photoUrl;
	private Context context;

	public PhotoViewPagerAdapter(Context context, List<String> photoUrl) {
		this.context = context;
		this.photoUrl = photoUrl;
	}

	@Override
	public int getCount() {
		return null == photoUrl ? 0 : photoUrl.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		PhotoView photoView = new PhotoView(container.getContext());
		ImageLoader.getInstance().displayImage(photoUrl.get(position), photoView, App.getInstance().getOptions());
		container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return photoView;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}
