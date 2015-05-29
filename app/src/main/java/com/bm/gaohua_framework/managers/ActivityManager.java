package com.bm.gaohua_framework.managers;

import java.util.Stack;

import android.app.Activity;


/**
 * 
 * Copyright © 2015 蓝色互动. All rights reserved.
 * @Description activity管理器
 * @author 高骅
 * @date 2015-4-22 下午5:14:04
 */
public final class ActivityManager {
	/**
	 * 保存Activity的栈
	 */
	private static Stack<Activity> activityStack;

	/**
	 * 单例
	 */
	private static ActivityManager instance;


	private ActivityManager() {
	}


	public static ActivityManager getAppManager() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}


	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}


	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}


	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}


	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}


	public void finishActivity(Class<?> cls) {
		Activity activity = null;
		for (Activity a : activityStack) {
			if (a.getClass().equals(cls)) {
				activity = a;
				break;
			}
		}
		if (activity != null)
			finishActivity(activity);
	}


	public Activity getActivity(Class<?> cls) {
		if (null != activityStack && activityStack.size() != 0) {
			int size = activityStack.size();
			for (int i = size - 1; i >= 0; i--) {
				Activity activity = activityStack.get(i);
				if (activity.getClass().equals(cls)) {
					return activity;
				}
			}
		}
		return null;
	}
	
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	public void appExit() {
		try {
			finishAllActivity();
			//System.exit(0);
		} catch (Exception e) {
		}
	}

	public Stack<Activity> getStack() {
		return activityStack;
	}
}