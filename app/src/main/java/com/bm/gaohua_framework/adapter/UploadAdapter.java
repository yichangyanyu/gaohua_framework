package com.bm.gaohua_framework.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * 加载图片适配器
 * @author qianyao
 *
 */
public class UploadAdapter extends BaseAdapter {

	private List<String> arrayList;
	private LayoutInflater inflater;
	private WeakHashMap<String, Bitmap> weakHashMap;
	private Context context;
	private HashMap<String, BitmapCompress> bitmapHashMap;

	public UploadAdapter(ArrayList<String> arrayList,Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.arrayList = arrayList;
		weakHashMap = new WeakHashMap<String, Bitmap>();
		bitmapHashMap = new HashMap<String, BitmapCompress>();
	}

	/**
	 * 当GridView数据发生变化时,调用此方法来更新GridView
	 * @param list
	 */
	public void updateListView(List<String> arrayList){
		this.arrayList = arrayList;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.photoalbumchoiceitem, null);
			holder.viewImg = (ImageView) convertView.findViewById(R.id.photoalbumchoiceitem_photo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String id = arrayList.get(position);
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
		return convertView;
	}

	class ViewHolder {
		ImageView viewImg;
	}

}
