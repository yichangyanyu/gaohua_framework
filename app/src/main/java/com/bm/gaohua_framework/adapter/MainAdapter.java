package com.bm.gaohua_framework.adapter;

import java.util.List;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.holder.ViewHolder;
import com.bm.gaohua_framework.interfaces.IAdapterBusinessLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 主页适配器
 * @author 高骅
 * @date 2015-3-19 下午7:59:46
 */
public class MainAdapter extends UniversalBaseAdapter<Object> implements IAdapterBusinessLogic{
	private LayoutInflater mInflater;
    private String data;
    private TextView tv_menu;
    
	public MainAdapter(Context context, List<Object> data) {
		super(context, data);
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.item_main_listview, null);
		}
		tv_menu = ViewHolder.get(convertView, R.id.tv_menu);
		getAdapterDate(position);
		setAdapterDate();
		return convertView;
	}

	@Override
	public void setAdapterDate() {
		tv_menu.setText(data);
	}

	@Override
	public void getAdapterDate(int position) {
		data = (String) getItem(position);
	}

	@Override
	public void handleBusiness() {
		// TODO Auto-generated method stub
		
	}

}
