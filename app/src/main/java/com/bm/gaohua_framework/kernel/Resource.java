package com.bm.gaohua_framework.kernel;

import android.content.Context;

/**
 * Copyright © 2015 蓝色互动. All rights reserved.
 * 
 * @Description 通过反射获取其ID值
 * @author 高骅
 * @date 2015-5-8 下午8:09:41
 */
public class Resource {

	/**
	 * 
	 * @Package com.bm.gaohua_framework.kernel
	 * @author 高骅
	 * @Description 通过反射获取资源ID layout 布局文件夹名 layoutName 布局文件名

	 * @return int
	 * @date 2015-5-8 下午8:10:49
	 */
	public static int getIdByName(Context context, String layout, String layoutName) {
		String packageName = context.getPackageName();
		Class r = null;
		int id = 0;
		try {
			r = Class.forName(packageName + ".R");
			Class[] classes = r.getClasses();
			Class desireClass = null;
			for (int i = 0; i < classes.length; ++i) {
				if (classes[i].getName().split("\\$")[1].equals(layout)) {
					desireClass = classes[i];
					break;
				}
			}
			if (desireClass != null)
				id = desireClass.getField(layoutName).getInt(desireClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return id;
	}
}
