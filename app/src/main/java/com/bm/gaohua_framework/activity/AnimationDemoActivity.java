package com.bm.gaohua_framework.activity;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.views.animview.AnimImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AnimationDemoActivity extends Activity implements IBaseActivity {
    private RelativeLayout relativeLayout;
    private AnimImageView animImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void close(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initView() {
		setContentView(R.layout.act_animation);
		relativeLayout=(RelativeLayout) this.findViewById(R.id.rl_anim);
		animImageView=new AnimImageView(this);
		relativeLayout.addView(animImageView);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

}
