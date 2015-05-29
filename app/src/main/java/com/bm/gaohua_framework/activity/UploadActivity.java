package com.bm.gaohua_framework.activity;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.UploadAdapter;

import android.R.fraction;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class UploadActivity extends Activity implements OnItemClickListener {
	private GridView gridView;
	Button button, buttonup;
	ArrayList<String> idList = new ArrayList<String>();
	ArrayList<String> pathList = new ArrayList<String>();
	private UploadAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//没有应用的名字
		setContentView(R.layout.act_upload);
		findView(); // 找到控件 并对两个按钮进行监听
		initData();//加载已选好的图片
		gridView.setOnItemClickListener(this);
	}

	private void findView() {
		gridView = (GridView) findViewById(R.id.gridView1);
		button = (Button) findViewById(R.id.button);
		buttonup = (Button) findViewById(R.id.buttonup);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UploadActivity.this,
						PhotoChoiceActivity.class);
				startActivity(intent);
				finish();
			}
		});

		buttonup.setOnClickListener(new OnClickListener() {
			// TODO Auto-generated method stub
			@Override
			public void onClick(View v) {
				// ---------- upload images
				for (int i = 0; i < pathList.size(); i++) {
					String s = pathList.get(i);
					Log.i("看看", "s = " + s);
					uploadFile(s, i + ".jpg");
				}
				Intent intent = new Intent();
				intent.setClass(UploadActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				Toast.makeText(UploadActivity.this, "上传成功", 1000).show();
			}
		});
	}
	
	//初始化数据
	private void initData() {
		ArrayList<HashMap<String, Object>> lst = new ArrayList<HashMap<String, Object>>();

		Intent intent = getIntent();
		idList = intent.getStringArrayListExtra("idList");
		pathList = intent.getStringArrayListExtra("pathList");
		adapter = new UploadAdapter(idList, this);
		if (idList != null) {
			adapter = new UploadAdapter(idList, this);
			gridView.setAdapter(adapter);
			for (String s : idList) {
				System.out.println(s + "-----");
				Log.i("qianyao", "打印出来的已添加到首页");
			}
		}		
	}
	

	//点击单个图片删除 事件
	/*
		arg0 The AdapterView where the click happened.
		arg1 The view within the AdapterView that was clicked (this will be a view provided by the adapter)
		arg2 The position of the view in the adapter.
		arg3 The row id of the item that was clicked.
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
			//创建构建器
			AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);//这里一定要是Activity的对象
			//设置数据给构建器
			builder.setIcon(android.R.drawable.ic_dialog_alert);//设置图标
			builder.setTitle("删除");//设置标题
			builder.setMessage("确定要删除相片吗？");//设置内容
			//设置确定按钮
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					//编写要执行删除代码
				 idList.remove(arg2);
				 adapter.updateListView(idList);
				}
			});
			//设置取消按钮
			builder.setNegativeButton("取消", null);
			AlertDialog dialog = builder.create();//创建对话框
			dialog.show();//显示
			
	}

	/* 上传文件至Server的方法 */
	private void uploadFile(String filepathname, String newName) {
		
	}
}






























