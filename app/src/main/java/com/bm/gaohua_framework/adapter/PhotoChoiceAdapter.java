package com.bm.gaohua_framework.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.utils.BitmapCompress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PhotoChoiceAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, String>> arrayList;
	private LayoutInflater inflater;
	private WeakHashMap<String, Bitmap> weakHashMap;
	private Context context;
	private HashMap<String, BitmapCompress> bitmapHashMap;

	public PhotoChoiceAdapter(
			ArrayList<HashMap<String, String>> arrayList, Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.arrayList = arrayList;
		weakHashMap = new WeakHashMap<String, Bitmap>();
		bitmapHashMap = new HashMap<String, BitmapCompress>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashMap = arrayList.get(position);
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.photoalbumchoiceitem, null);
			holder.viewImg = (ImageView) convertView
					.findViewById(R.id.photoalbumchoiceitem_photo);
			holder.checkImg = (ImageView) convertView
					.findViewById(R.id.photoalbumchoiceitem_sure);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		String id = hashMap.get("id");
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), 0);
		if (weakHashMap.get(id) == null && bitmapHashMap.get(id) == null) {
			BitmapCompress bitmapCompress = new BitmapCompress(context,
					holder.viewImg, weakHashMap, bitmapHashMap);
			bitmapCompress.execute(id);
			bitmapHashMap.put(id, bitmapCompress);
		} else {
			bitmap = weakHashMap.get(id);
		}
		holder.viewImg.setImageBitmap(bitmap);
		if (hashMap.get("check") != null && hashMap.get("check").equals("true")) {
			holder.checkImg
					.setBackgroundResource(R.drawable.photoalbumchoiceitem_sure);
		} else {
			holder.checkImg.setBackgroundResource(0);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView viewImg;
		ImageView checkImg;
	}

}
