package com.bm.gaohua_framework.utils;

/**
 * 
 * Copyright © 2014 蓝色互动. All rights reserved.
 * 
 * @Description String工具类
 * @author 高骅
 * @date 2014-10-22 下午1:28:04
 */
public class StringUtil {

	private static StringUtil stringUtil;

	public static StringUtil getInstance() {
		if (stringUtil == null) {
			stringUtil = new StringUtil();
		}
		return stringUtil;
	};

	/**
	 * 
	 * @author 高骅
	 * @Description 判断字符串里是否有空格 返回-1 没空格
	 * @return true没空格 false有空格
	 * @date 2014-10-22 下午1:28:41
	 */
	public boolean hasSpace(String s) {
		int i = s.indexOf(" ");
		if (i == -1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @author 高骅
	 * @Description 字符串替换方法 身份证 电话 其他都可以替换
	 * @return String
	 * @param str需要被替换的内容
	 * @param start
	 *            替换开始的位置
	 * @param end
	 *            替换结束的位置
	 * @param replaceStr
	 *            输入你替换的内容
	 * @date 2014-11-3 下午2:35:25
	 */
	public String replaceStr(String str, int start, int end, String replaceStr) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(str);
		buffer.delete(start, end);
		buffer.insert(start, replaceStr);
		return buffer.toString();
	}
}
