package com.bm.gaohua_framework.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description sd卡工具类
 * @author 高骅
 * @date 2015-4-22 下午3:23:54
 */
public class SdCardUtil {

	private static SdCardUtil sdCardUtil;

	private SdCardUtil() {
	}

	public static SdCardUtil getInstants() {
		if (sdCardUtil == null) {
			sdCardUtil = new SdCardUtil();
		}
		return sdCardUtil;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 判断SDCard是否可用
	 * @return boolean
	 * @date 2015-4-22 下午3:26:20
	 */
	public boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取SD卡路径
	 * @return String
	 * @date 2015-4-22 下午3:26:09
	 */
	public String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取SD卡的剩余容量 单位byte
	 * @return long
	 * @date 2015-4-22 下午3:26:03
	 */
	public long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 * @return long
	 * @date 2015-4-22 下午3:25:46
	 */
	public long getFreeBytes(String filePath) {
		// 如果是sd卡的下的路径，则获取sd卡可用容量
		if (filePath.startsWith(getSDCardPath())) {
			filePath = getSDCardPath();
		} else {// 如果是内部存储的路径，则获取内存存储的可用容量
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取系统存储路径
	 * @return String
	 * @date 2015-4-22 下午3:25:25
	 */
	public String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}

	/**
	 * 
	 * @Package com.bm.gaohua_framework.utils
	 * @author 高骅
	 * @Description 获取手机本身的可用的存储空间
	 * @return String
	 * @date 2015-4-22 下午3:28:04
	 */
	public String getPhoneAvailSpace(Context context) {
		File file = Environment.getDataDirectory();
		StatFs statFs = new StatFs(file.getAbsolutePath());
		long availableBlocks = statFs.getAvailableBlocks();
		int blockSize = statFs.getBlockSize();
		long size = availableBlocks * blockSize;
		return Formatter.formatFileSize(context, size);
	}

}
