package com.bm.gaohua_framework.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.widget.TextView;
public class IndicatorFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		TextView tv=new TextView(getActivity());
		tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(30);
		Bundle mBundle = getArguments();
		String title = mBundle.getString("title");
		tv.setText(title);
		return tv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	
}