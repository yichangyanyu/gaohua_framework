package com.bm.gaohua_framework.activity;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.views.RadiationView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RadiationActivity extends Activity {

	private ImageView iv = null;
	private RadiationView rv = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_radiation);

		iv = (ImageView) findViewById(R.id.iv_rotate);
		rv = (RadiationView) findViewById(R.id.rv);
		rv.setMinRadius(70);// 辐射半径
		rv.startRadiate();// 开始辐射
		Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.rotate_circle_anim);
		iv.startAnimation(anim);// 开始动画
		
	}
	
	//写一个触摸
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.BUTTON_BACK){
			finish();
		};
		return super.onTouchEvent(event);
	}

}
