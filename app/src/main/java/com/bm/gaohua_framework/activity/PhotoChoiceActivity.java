package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.PhotoChoiceAdapter;

import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;

public class PhotoChoiceActivity extends Activity implements
		OnItemClickListener {

	private ArrayList<HashMap<String, String>> arrayList;
	private Map<Integer,String> localPicMap;
	private GridView gridView;
	private PhotoChoiceAdapter adapter;
	private Button button;
	private ArrayList<String>idList=new ArrayList<String>();
	private ArrayList<String> pathList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_photo_choice);
		button=(Button)findViewById(R.id.button1);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						LOGINFO();
						for(int i=0;i<arrayList.size();i++){
							if(("true").equals(arrayList.get(i).get("check"))){
								//路径
								//localPicMap.get(i);
								idList.add(arrayList.get(i).get("id"));
								pathList.add(arrayList.get(i).get("path"));
								Log.i("qianyao", "select id = "+arrayList.get(i).get("id"));
								Log.i("qianyao", "select image_path = "+arrayList.get(i).get("path"));
							}
							
						}
						
						Intent intent =new Intent();
						intent.putStringArrayListExtra("idList",idList);
						intent.putStringArrayListExtra("pathList",pathList);
						intent.setClass(PhotoChoiceActivity.this, UploadActivity.class);
						startActivity(intent);
						finish();
					}
				});
		initView();
	}

	private void initView() {
		arrayList = new ArrayList<HashMap<String, String>>();
		gridView = (GridView) findViewById(R.id.gridView);
		adapter = new PhotoChoiceAdapter(arrayList, this);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this);

		getColumnData();
		adapter.notifyDataSetChanged();
	}

	private void getColumnData() {
		localPicMap=new HashMap<Integer,String>();
		ContentResolver testcr = getContentResolver();
		String[] projection = { MediaStore.Images.Media.DATA,
				MediaStore.Images.Media._ID };
		Cursor cur = testcr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection, null, null, null);
		System.out.println("do this");
		for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
			int _id;
			int image_id;
			String image_path;

			int _idColumn = cur.getColumnIndex(Thumbnails._ID);
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);
			_id = cur.getInt(_idColumn);
			image_path = cur.getString(dataColumn);
			System.out.println(cur.getPosition()+"position----------------------");
			System.out.println("path"+image_path+"=====================");
			localPicMap.put(cur.getPosition(), image_path);
			// Do something with the values.
			HashMap<String, String> hash = new HashMap<String, String>();
			hash.put("id", String.valueOf(_id));
			hash.put("path", image_path);
			Log.i("qianyao", "_id = "+_id);
			Log.i("qianyao", "image_path = "+image_path);
			arrayList.add(hash);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		HashMap<String, String> hashMap = arrayList.get(position);
		if (hashMap.get("check") != null && hashMap.get("check").equals("true"))
			hashMap.put("check", "false");
		else
			hashMap.put("check", "true");
		arrayList.set(position, hashMap);
		adapter.notifyDataSetChanged();
		//遍历hashMap 获取check 若为false则为选中
	}
	
	public void LOGINFO() {
		if(arrayList != null) {
			for(int i=0; i<arrayList.size(); i++){
				HashMap hm = arrayList.get(i);
				Log.i("qianyao", "hm.getcheck = "+hm.get("check"));
				Log.i("qianyao", "hm.getid = "+hm.get("id"));
			}
		}
	}
}
