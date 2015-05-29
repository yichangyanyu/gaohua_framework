package com.bm.gaohua_framework.app;

import com.bm.gaohua_framework.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.app.Application;
import android.content.Context;

public class App extends Application{
	// 单例对象
	private static App instance;
	// 上下文对象
	public static Context applicationContext;
	// 获取单例对象
	public static App getInstance() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationContext = this;
		instance = this;
		initImageLoader();
	}
	
	/**
	 * 
	 * @author 高骅
	 * @Description 初始化imageloader
	 * @return void
	 * @date 2015-2-27 上午10:42:57
	 */
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) // //
				.tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}

	/**
	 * 
	 * @author 高骅
	 * @Description ImagerLoader显示设置 方形
	 * @return DisplayImageOptions
	 * @date 2014-11-23 上午2:47:36
	 */
	public DisplayImageOptions getOptions() {
		return new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.loading_on_img).showImageForEmptyUri(R.drawable.loading_empty_img).showImageOnFail(R.drawable.loading_fail_img).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
	}

	/**
	 * 
	 * @author 高骅
	 * @Description ImagerLoader显示设置 圆形
	 * @return DisplayImageOptions
	 * @date 2014-11-23 上午2:47:36
	 */
	public DisplayImageOptions getOptionsCircle() {
		return new DisplayImageOptions.Builder().showImageOnLoading(0).showImageForEmptyUri(0).displayer(new RoundedBitmapDisplayer(360)).showImageOnFail(0).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
	}

}
