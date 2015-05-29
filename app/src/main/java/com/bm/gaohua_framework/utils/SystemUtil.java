package com.bm.gaohua_framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 系统工具类
 * @author 杨杰 吴佳明
 * @date 2015-4-17 下午4:53:35
 */
public class SystemUtil {

	private static SystemUtil systemUtil;

	private SystemUtil() {
	}

	public static SystemUtil getInstants() {
		if (systemUtil == null) {
			systemUtil = new SystemUtil();
		}
		return systemUtil;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 判断应用程序是否是用户程序
	 * @return boolean
	 * @date 2015-4-22 下午3:27:34
	 */
	public boolean isUserApp(ApplicationInfo info) {

		if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
			return true;

		} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 判断应用程序是否安装在sdcard
	 * @return boolean
	 * @date 2015-4-22 下午3:27:24
	 */
	public boolean isInstallSdcard(ApplicationInfo info) {
		if ((info.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取到现在系统正在运行进程数
	 * @return int
	 * @date 2015-4-22 下午3:27:14
	 */
	public int getRunningAppProcess(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		return runningAppProcesses.size();
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取手机所有的内存
	 * @return String
	 * @date 2015-4-22 下午3:27:04
	 */
	public String getSystemTotalMemory(Context context) {
		String totalMem = null;
		try {
			File file = new File("/proc/meminfo");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String readLine = br.readLine();// MemTotal: 513000 kB

			String content = (readLine.split(":")[1]).trim().split(" ")[0];
			long size = Integer.parseInt(content) * 1024l;
			totalMem = Formatter.formatFileSize(context, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalMem;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取手机可用的内存
	 * @return String
	 * @date 2015-4-22 下午3:26:49
	 */
	public String getSystemFreeMemory(Context context) {
		String freeMem = null;
		try {
			File file = new File("/proc/meminfo");
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			String readLine = br.readLine();// MemFree: 288776 kB
			String content = (readLine.split(":")[1]).trim().split(" ")[0];
			long size = Integer.parseInt(content) * 1024l;
			freeMem = Formatter.formatFileSize(context, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freeMem;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 打开软键盘
	 * @return void
	 * @date 2015-4-22 下午3:20:43
	 */
	public void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 关闭软键盘
	 * @return void
	 * @date 2015-4-22 下午3:20:57
	 */
	public void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

}
