package com.bm.gaohua_framework.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 
 * Copyright © 2014 高骅. All rights reserved.
 * 
 * @Description Version工具类 取得VersionName VersionCode
 * @author 高骅
 * @date 2014-10-19 上午12:27:38
 */

public class VersionUtil {

	private static VersionUtil versionUtil;

	public static VersionUtil getInstanc() {
		if (versionUtil==null) {
			versionUtil = new VersionUtil();
		}
		return versionUtil;
	}

	private VersionUtil() {
	}

	/**
	 * 
	 * @author 高骅
	 * @Description 得到VerName
	 * @return String
	 * @date 2014-10-19 上午12:29:17
	 */
	public String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verName;

	}

	/**
	 * 
	 * @author 高骅
	 * @Description 得到VerCode
	 * @return int
	 * @date 2014-10-19 上午12:29:01
	 */
	public int getVerCode(Context context) {
		int verCode = 0;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;

	}
}
