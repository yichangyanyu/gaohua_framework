package com.bm.gaohua_framework.adapter;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 适配器基类
 * @author 高骅
 * @param <T>
 * @date 2015-3-12 下午1:13:51
 */
public class UniversalBaseAdapter<T> extends BaseAdapter {
	/**
	 * 上下文
	 */
	private Context context;
	/**
	 * 适配器数据
	 */
	private List<T> data;
	/**
	 * 刷新界面用的handler
	 */
	private Handler handler;

	private int resId;

	private String type;

	/**
	 * 
	 * @Package com.bm.thesilkroadcarnival.adapter
	 * @Description 不带handler
	 * @param context
	 * @param data
	 */
	public UniversalBaseAdapter(Context context, List<T> data) {
		this.context = context;
		this.data = data;

	}

	/**
	 * 
	 * @Description 加载不同的布局
	 * @param context
	 * @param data
	 * @param resId
	 * @param type
	 */
	public UniversalBaseAdapter(Context context, List<T> data, int resId, String type) {
		this.context = context;
		this.data = data;
		this.resId = resId;
		this.type = type;
	}

	/**
	 * 
	 * @Package com.bm.thesilkroadcarnival.adapter
	 * @Description 带handler
	 * @param context
	 * @param data
	 * @param handler
	 */
	public UniversalBaseAdapter(Context context, List<T> data, Handler handler) {
		this.context = context;
		this.data = data;
		this.handler = handler;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return null == data ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null == data ? 0 : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return convertView;
	}

}
