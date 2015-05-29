package com.bm.gaohua_framework.adapter;

import java.util.List;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.holder.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ClassInfoAdapter extends UniversalBaseAdapter<Object> {
	private LayoutInflater mInflater;

	public ClassInfoAdapter(Context context, List<Object> data) {
		super(context, data);
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = mInflater.inflate(R.layout.item_class_info, null);
		}
		TextView tv_info = ViewHolder.get(convertView, R.id.tv_info);
		String data = (String) getItem(position);
		tv_info.setText("public方法:" + data);
		return convertView;
	}

}
