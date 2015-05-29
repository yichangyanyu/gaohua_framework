package com.bm.gaohua_framework.activity;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.bm.gaohua_framework.utils.ToastUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description 播放Gif图
 * @author 高骅
 * @date 2015-5-14 下午2:47:18
 */
public class GifActivity extends Activity implements IBaseActivity {
	private GifView gifView, gifView2, gifView3,gifView4,gifView5,gifView6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	@Override
	public void close(View v) {
		this.finish();
	}

	@Override
	public void initView() {
		setContentView(R.layout.act_gif);
		gifView = (GifView) this.findViewById(R.id.iv_gif);
		gifView2 = (GifView) this.findViewById(R.id.iv_gif2);
		gifView3 = (GifView) this.findViewById(R.id.iv_gif3);
		gifView4=(GifView) this.findViewById(R.id.iv_gif4);
		gifView5=(GifView) this.findViewById(R.id.iv_gif5);
		gifView6=(GifView) this.findViewById(R.id.iv_gif6);

	}

	@Override
	public void initData() {
		ToastUtil.getInterface().showToast(this,"浩浩说他是大帅比！", 1);
		gifView.setGifImage(R.drawable.head_meitu_1);
		gifView.setGifImageType(GifImageType.COVER);

		gifView2.setGifImage(R.drawable.head_meitu_2);
		gifView2.setGifImageType(GifImageType.COVER);

		gifView3.setGifImage(R.drawable.head_meitu_3);
		gifView3.setGifImageType(GifImageType.COVER);
		
		gifView4.setGifImage(R.drawable.head_meitu_4);
		gifView4.setGifImageType(GifImageType.COVER);
		
		gifView5.setGifImage(R.drawable.head_meitu_5);
		gifView5.setGifImageType(GifImageType.COVER);
		
		gifView6.setGifImage(R.drawable.head_meitu_6);
		gifView6.setGifImageType(GifImageType.COVER);
	}

}
