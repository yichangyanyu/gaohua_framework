package com.bm.gaohua_framework.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;













import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.adapter.SortAdapter;
import com.bm.gaohua_framework.beans.SortModel;
import com.bm.gaohua_framework.utils.AsyncTaskBase;
import com.bm.gaohua_framework.utils.CharacterParser;
import com.bm.gaohua_framework.utils.ConstactUtil;
import com.bm.gaohua_framework.utils.PinyinComparator;
import com.bm.gaohua_framework.wight.ClearEditText;
import com.bm.gaohua_framework.wight.LoadingView;
import com.bm.gaohua_framework.wight.SideBar;
import com.bm.gaohua_framework.wight.SideBar.OnTouchingLetterChangedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContactsActivity extends Activity {

	private Context mContext;
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private ImageButton shezhi,back;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	private Map<String, String> callRecords;
	private LoadingView mLoadingView;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.act_contacts);
		mContext=ContactsActivity.this;
		findView();	
		initData();
	}

	/*
	 *  找到视图控件
	 */
	private void findView() {
		//正在加载的显示
		mLoadingView=(LoadingView) findViewById(R.id.loading);
		//触摸事件,右侧字母选择条控件
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		//中间显示小文本对话框，提示用户所选择的字母
		dialog = (TextView) findViewById(R.id.dialog);
		//搜索框下的排序ListView
		sortListView = (ListView)findViewById(R.id.country_lvcountry);
		//设置按钮
		shezhi = (ImageButton) findViewById(R.id.shezhi);
		//结束应用程序按钮
		back = (ImageButton) findViewById(R.id.back);
		
	}

	private void initData() {

		// 实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		//根据拼音来排列ListView里面的数据类
		pinyinComparator = new PinyinComparator();

		sideBar.setTextView(dialog);

		// 设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@SuppressLint("NewApi")
			@Override
			public void onTouchingLetterChanged(String s) {
				// 该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}
			}
		});
		
		//滑动排序ListView设点击事件监听
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 这里要利用adapter.getItem(position)来获取当前position所对应的对象
				// Toast.makeText(getApplication(),
				// ((SortModel)adapter.getItem(position)).getName(),
				// Toast.LENGTH_SHORT).show();
				String number = callRecords.get(((SortModel) adapter
						.getItem(position)).getName());

			}
		});

			
			back.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			
			
		
		new AsyncTaskConstact(mLoadingView).execute(0);
	}

	private class AsyncTaskConstact extends AsyncTaskBase{

		public AsyncTaskConstact(LoadingView loadingView) {
			super(loadingView);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected Integer doInBackground(Integer... params) {
			int result = -1;
			callRecords = ConstactUtil.getAllCallRecords(mContext);
			result = 1;
			return result;
		}
		
		@Override
		protected void onPostExecute(Integer result) {

			super.onPostExecute(result);
			if (result == 1) {
				List<String> constact = new ArrayList<String>();
				for (Iterator<String> keys = callRecords.keySet().iterator(); keys
						.hasNext();) {
					String key = keys.next();
					constact.add(key);
				}
				String[] names = new String[] {};
				names = constact.toArray(names);
				SourceDateList = filledData(names);

				// 根据a-z进行排序源数据
				Collections.sort(SourceDateList, pinyinComparator);
				adapter = new SortAdapter(mContext, SourceDateList);
				sortListView.setAdapter(adapter);

				mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

				// 根据输入框输入值的改变来过滤搜索
				mClearEditText.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
						filterData(s.toString());
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {

					}

					@Override
					public void afterTextChanged(Editable s) {
					}
				});
			}
		
		}
		
	}
	
	
	private List<SortModel> filledData(String[] date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.length; i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}
	
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}


















































