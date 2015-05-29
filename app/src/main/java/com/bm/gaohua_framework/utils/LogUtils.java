package com.bm.gaohua_framework.utils;

import android.util.Log;

/**
 * 自定义日志类 [一句话功能简述] [功能详细描述]
 * 
 * @作者 mWX160443
 * @version [版本号, 2013-5-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class LogUtils {

	/**
	 * 是否打开LOG
	 */
	private static boolean isLogEnabled = true;

	/**
	 * LOG默认TAG
	 */
	private static String defaultTag = "LogUtils";

	/**
     * 
     */
	private static final String TAG_CONTENT_PRINT = "%s:%s.%s:%d";

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private static StackTraceElement getCurrentStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];

	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述] 打印LOG
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void trace() {
		if (isLogEnabled) {
			android.util.Log.d(defaultTag, getContent(getCurrentStackTraceElement()));
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述] 获取LOG
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private static String getContent(StackTraceElement trace) {
		return String.format(TAG_CONTENT_PRINT, defaultTag, trace.getClassName(), trace.getMethodName(), trace.getLineNumber());
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]打印默认TAG的LOG
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void traceStack() {
		if (isLogEnabled) {
			traceStack(defaultTag, android.util.Log.ERROR);
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]打印Log当前调用栈信息
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void traceStack(String tag, int priority) {

		if (isLogEnabled) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			android.util.Log.println(priority, tag, stackTrace[4].toString());
			StringBuilder str = new StringBuilder();
			String prevClass = null;
			for (int i = 5; i < stackTrace.length; i++) {
				String className = stackTrace[i].getFileName();
				int idx = className.indexOf(".java");
				if (idx >= 0) {
					className = className.substring(0, idx);
				}
				if (prevClass == null || !prevClass.equals(className)) {

					str.append(className.substring(0, idx));

				}
				prevClass = className;
				str.append(".").append(stackTrace[i].getMethodName()).append(":").append(stackTrace[i].getLineNumber()).append("->");
			}
			android.util.Log.println(priority, tag, str.toString());
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]指定TAG和指定内容的方法
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void d(String tag, String msg) {
		if (isLogEnabled) {
			Log.d(tag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述] 默认TAG和制定内容的方法
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void d(String msg) {
		if (isLogEnabled) {
			Log.d(defaultTag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void i(String tag, String msg) {
		if (isLogEnabled) {
			Log.i(tag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * 
	 * [一句话功能简述] [功能详细描述]
	 * 
	 * @param [参数1] [参数1说明]
	 * @param [参数2] [参数2说明]
	 * @return [返回类型说明]
	 * @exception/throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void w(String tag, String msg) {
		if (isLogEnabled) {
			Log.w(tag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用)警告级日志
	 * 
	 * @author wangjie 2015-4-17 下午2:14:24
	 * @param tag
	 *            日志标签
	 * @param msg
	 *            日志消息
	 * @return void
	 */
	public static void e(String tag, String msg) {
		if (isLogEnabled) {
			Log.e(tag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}

	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 警告级日志（使用默认标签）
	 * 
	 * @author wangjie 2015-4-17 下午2:13:58
	 * @param msg
	 *            日志消息
	 * @return void
	 */
	public static void e(String msg) {
		if (isLogEnabled) {
			Log.e(defaultTag, getContent(getCurrentStackTraceElement()) + ">" + msg);
		}
	}
}
