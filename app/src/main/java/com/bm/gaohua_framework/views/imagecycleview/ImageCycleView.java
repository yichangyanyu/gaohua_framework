package com.bm.gaohua_framework.views.imagecycleview;

import java.util.ArrayList;

import com.bm.gaohua_framework.R;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * 广告图片自动轮播控件</br>
 * 
 * <pre>
 *   集合ViewPager和指示器的一个轮播控件，主要用于一般常见的广告图片轮播，具有自动轮播和手动轮播功能 
 *   使用：只需在xml文件中使用{@code <com.minking.imagecycleview.ImageCycleView/>} ，
 *   然后在页面中调用  {@link #setImageResources(ArrayList, ImageCycleViewListener) }即可!
 *   
 *   另外提供{@link #startImageCycle() } \ {@link #pushImageCycle() }两种方法，用于在Activity不可见之时节省资源；
 *   因为自动轮播需要进行控制，有利于内存管理
 * </pre>
 * 
 * @author minking
 */
public class ImageCycleView extends LinearLayout {

	/**
	 * 上下文
	 */
	private Context mContext;

	/**
	 * 图片轮播视图
	 */
	private ViewPager mAdvPager = null;

	/**
	 * 滚动图片视图适配器
	 */
	private ImageCycleAdapter mAdvAdapter;

	/**
	 * 图片轮播指示器控件
	 */
	private ViewGroup mGroup;

	/**
	 * 图片轮播指示器-个图
	 */
	private ImageView mImageView = null;

	/**
	 * 滚动图片指示器-视图列表
	 */
	private ImageView[] mImageViews = null;

	/**
	 * 图片滚动当前图片下标
	 */
	private int mImageIndex = 0;

	/**
	 * 手机密度
	 */
	private float mScale;

	/**
	 * 设置是否手动无限循环
	 */
	private boolean isManualLoop = false;

	private int size;

	/**
	 * @param context
	 */
	public ImageCycleView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mScale = context.getResources().getDisplayMetrics().density;
		LayoutInflater.from(context).inflate(R.layout.ad_cycle_view, this);
		mAdvPager = (ViewPager) findViewById(R.id.adv_pager);
		mAdvPager.setOnPageChangeListener(new GuidePageChangeListener());
		mAdvPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					// 开始图片滚动
					startImageTimerTask(2000);
					break;
				default:
					// 停止图片滚动
					stopImageTimerTask();
					break;
				}
				return false;
			}
		});
		// 滚动图片右下指示器视图
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);
	}

	/**
	 * 装填图片数据
	 * 
	 * @param imageUrlList
	 * @param imageCycleViewListener
	 */
	public void setImageResources(ArrayList<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {
		// 清除所有子视图
		mGroup.removeAllViews();
		// 图片广告数量
		// final int imageCount = imageUrlList.size();
		size = imageUrlList.size();

		mImageViews = new ImageView[size];
		// 图片指示器
		/*
		 * for (int i = 0; i < imageCount; i++) { mImageView = new
		 * ImageView(mContext); int imageParams = (int) (mScale * 20 + 0.5f);//
		 * PX与DP转换，适应不同分辨率 int imagePadding = (int) (mScale * 5 + 0.5f);
		 * mImageView.setLayoutParams(new LayoutParams(imageParams,
		 * imageParams)); mImageView.setPadding(imagePadding, imagePadding,
		 * imagePadding, imagePadding); mImageViews[i] = mImageView; if (i == 0)
		 * { mImageViews[i].setBackgroundResource(R.drawable.banner_dian_focus);
		 * } else {
		 * mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur); }
		 * mGroup.addView(mImageViews[i]); }
		 */
		// 颜色指示器 铺满整行
		for (int i = 0; i < size; i++) {
			View point = new View(mContext);
			point.setBackgroundResource(R.drawable.pager_select_line);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
			param.leftMargin = 2;
			param.rightMargin = 2;
			param.weight = 1;
			point.setLayoutParams(param);
			mGroup.addView(point);
		}
		// RelativeLayout.LayoutParams param1 = new
		// RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
		// 3);
		// mGroup.setLayoutParams(param1);
		// 如果上面两行注释了，则需要在布局文件中设置mGroup的高度，此高度就是指示器的高度
		mGroup.getChildAt(0).setSelected(true);

		mAdvAdapter = new ImageCycleAdapter(mContext, imageUrlList, imageCycleViewListener);
		mAdvPager.setAdapter(mAdvAdapter);
		startImageTimerTask(2000);
	}

	public void setIsManualLoop(boolean isLoop) {
		this.isManualLoop = isLoop;
	}

	/**
	 * 开始轮播(手动控制自动轮播与否，便于资源控制)
	 */
	public void startImageCycle() {
		startImageTimerTask(2000);
	}

	/**
	 * 暂停轮播——用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 开始图片滚动任务
	 */
	private void startImageTimerTask(long delayMillis) {
		stopImageTimerTask();
		// 图片每3秒滚动一次
		mHandler.postDelayed(mImageTimerTask, delayMillis);
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (mImageViews != null) {
				mImageIndex++;
				if (!isManualLoop) {
					// 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
					if ((mImageIndex) == size) {
						mImageIndex = 0;
					}

				}
				mAdvPager.setCurrentItem(mImageIndex);
			}
		}
	};

	/**
	 * 轮播图片状态监听器
	 * 
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask(2000); // 开始下次计时
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			// 设置当前显示的图片下标
			mImageIndex = index;

			int count = mImageViews.length;
			int p;
			if (isManualLoop) {
				p = index % count;
			} else {
				p = index;
			}

			// 设置图片滚动指示器背景
			// 图片指示器

			// mImageViews[index].setBackgroundResource(R.drawable.banner_dian_focus);
			// for (int i = 0; i < count; i++) {
			// if (index != i) {
			// mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
			// }
			// }

			// 颜色指示器 铺满整行
			for (int i = 0; i < count; i++) {

				mGroup.getChildAt(i).setSelected(i == p);
			}

		}

	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<ImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList<String> mAdList = new ArrayList<String>();

		/**
		 * 广告图片点击监听器
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList<String> adList, ImageCycleViewListener imageCycleViewListener) {
			mContext = context;
			mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList();
		}

		@Override
		public int getCount() {
			// return mAdList.size();
			return isManualLoop ? Integer.MAX_VALUE : mAdList.size();
		}

		private int getPosition(int position) {
			return isManualLoop ? position % mAdList.size() : position;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = mAdList.get(getPosition(position));
			ImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

			} else {
				imageView = mImageViewCacheList.remove(0);
			}
			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(getPosition(position), v);
				}
			});
			imageView.setTag(imageUrl);
			container.addView(imageView);
			mImageCycleViewListener.displayImage(imageUrl, imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			container.removeView(view);
			mImageViewCacheList.add(view);
		}

	}

	/**
	 * 轮播控件的监听事件
	 * 
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 加载图片资源
		 * 
		 * @param imageURL
		 * @param imageView
		 */
		public void displayImage(String imageURL, ImageView imageView);

		/**
		 * 单击图片事件
		 * 
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(int position, View imageView);
	}

}
