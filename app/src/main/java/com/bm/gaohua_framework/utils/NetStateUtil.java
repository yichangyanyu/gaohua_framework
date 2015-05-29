package com.bm.gaohua_framework.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 网络工具
 * @author 高骅
 * @date 2015-4-22 下午3:10:04
 */
public class NetStateUtil {

	private static NetStateUtil netStateUtil;

	private NetStateUtil() {

	}

	public static NetStateUtil getInstants() {
		if (netStateUtil == null) {
			netStateUtil = new NetStateUtil();
		}
		return netStateUtil;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 判断网络是否连接
	 * @return boolean
	 * @date 2015-4-22 下午3:17:16
	 */
	public boolean isNetConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 判断wifi是否连接
	 * @return boolean
	 * @date 2015-4-22 下午3:17:23
	 */
	public boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null)
			return false;
		return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 打开网络设置界面
	 * @return void
	 * @date 2015-4-22 下午3:17:40
	 */
	public void openNetSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}

}
