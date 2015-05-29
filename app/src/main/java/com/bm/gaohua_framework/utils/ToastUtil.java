package com.bm.gaohua_framework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * Copyright © 2014 蓝色互动. All rights reserved.
 * 
 * @Description Toast工具
 * @author 高骅
 * @date 2014-11-10 下午3:39:50
 */
public class ToastUtil {
	private static Toast mToast;
	private static ToastUtil toastUtil;

	private ToastUtil() {
	}

	public static ToastUtil getInterface() {
		if (toastUtil == null) {
			toastUtil = new ToastUtil();
		}
		return toastUtil;
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 显示Toast
	 * @return void
	 * @date 2014-11-10 下午3:52:55
	 */
	public void showToast(Context context, String toastText, int duration) {
		if (mToast != null) {
			mToast.setText(toastText);
			mToast.setDuration(duration);
			mToast.show();
		} else {
			mToast = Toast.makeText(context, toastText, duration);
			mToast.show();
		}
	}
}
